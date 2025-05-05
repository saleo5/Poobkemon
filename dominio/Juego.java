package dominio;

import java.util.List;
import dominio.pokemon.*;
import dominio.items.*;

public class Juego {
    private Entrenador jugador1;
    private Entrenador jugador2;
    private boolean turnoJugador1;
    private ModoJuego modo;
    private StringBuilder logBatalla; // Para reemplazar txtLog

    public Juego(ModoJuego modo, String nombreJugador1, String nombreJugador2,
                 List<Pokemon> pokemonesJugador1, List<Pokemon> pokemonesJugador2,
                 List<Item> itemsJugador1, List<Item> itemsJugador2) {
        this.modo = modo;
        this.jugador1 = new Entrenador(nombreJugador1);
        this.jugador2 = new Entrenador(nombreJugador2);
        this.turnoJugador1 = true;
        this.logBatalla = new StringBuilder();

        // Configurar equipos
        for (Pokemon pokemon : pokemonesJugador1) {
            if (pokemon != null) {
                this.jugador1.agregarPokemon(pokemon);
            }
        }

        for (Pokemon pokemon : pokemonesJugador2) {
            if (pokemon != null) {
                this.jugador2.agregarPokemon(pokemon);
            }
        }

        // Configurar ítems
        if (itemsJugador1 != null) {
            for (Item item : itemsJugador1) {
                if (item != null) {
                    this.jugador1.agregarItem(item);
                }
            }
        }

        if (itemsJugador2 != null) {
            for (Item item : itemsJugador2) {
                if (item != null) {
                    this.jugador2.agregarItem(item);
                }
            }
        }
    }

    public void ejecutarTurno(int accion, int parametro) {
        // 1. Determinar jugadores activos
        Entrenador jugadorActivo = turnoJugador1 ? jugador1 : jugador2;
        Entrenador oponente = turnoJugador1 ? jugador2 : jugador1;

        // 2. Verificar si el Pokémon activo puede actuar
        if (jugadorActivo.getPokemonActivo().estaDebilitado()) {
            agregarLog(jugadorActivo.getNombre() + " no puede actuar con un Pokémon debilitado!");
            return;
        }

        // 3. Registrar acción en el log
        agregarLog("\n--- TURNO DE " + jugadorActivo.getNombre().toUpperCase() + " ---");

        // 4. Procesar la acción
        switch (accion) {
            case 0: // Ataque
                procesarAtaque(jugadorActivo, oponente, parametro);
                break;

            case 1: // Cambio de Pokémon
                procesarCambioPokemon(jugadorActivo, parametro);
                break;

            case 2: // Uso de ítem
                procesarUsoItem(jugadorActivo, parametro);
                break;
        }

        // 5. Verificar si el oponente fue debilitado
        if (oponente.getPokemonActivo().estaDebilitado()) {
            agregarLog(oponente.getPokemonActivo().getNombre() + " fue debilitado!");

            if (!oponente.estaDerrotado()) {
                cambiarPokemonAutomatico(oponente);
            }
        }

        // 6. Cambiar turno solo si ambos Pokémon siguen activos
        if (!jugadorActivo.getPokemonActivo().estaDebilitado() &&
                !oponente.getPokemonActivo().estaDebilitado()) {
            turnoJugador1 = !turnoJugador1;
            agregarLog("¡Turno de " + (turnoJugador1 ? jugador1.getNombre() : jugador2.getNombre()) + "!");
        }

        // 7. Verificar fin de batalla
        verificarFinBatalla();
    }

    // Métodos auxiliares
    private void procesarAtaque(Entrenador atacante, Entrenador defensor, int indiceMovimiento) {
        Movimiento movimiento = atacante.getPokemonActivo().getMovimiento(indiceMovimiento);
        if (movimiento == null || movimiento.getPp() <= 0) {
            agregarLog("Movimiento no disponible!");
            return;
        }

        movimiento.reducirPp();
        int danio = calcularDanio(atacante.getPokemonActivo(),
                defensor.getPokemonActivo(),
                movimiento);

        defensor.getPokemonActivo().recibirDanio(danio);
        agregarLog(atacante.getNombre() + " usó " + movimiento.getNombre() + "!");
    }

    private void procesarCambioPokemon(Entrenador entrenador, int indicePokemon) {
        if (indicePokemon >= 0 && indicePokemon < entrenador.getEquipo().size()) {
            Pokemon nuevo = entrenador.getEquipo().get(indicePokemon);
            if (!nuevo.estaDebilitado()) {
                entrenador.cambiarPokemonActivo(indicePokemon);
                agregarLog(entrenador.getNombre() + " cambió a " + nuevo.getNombre());
            } else {
                agregarLog("¡No puedes cambiar a un Pokémon debilitado!");
            }
        }
    }

    private void procesarUsoItem(Entrenador entrenador, int indiceItem) {
        if (indiceItem >= 0 && indiceItem < entrenador.getMochila().size()) {
            Item item = entrenador.getMochila().get(indiceItem);
            item.usar(entrenador.getPokemonActivo());
            entrenador.removerItem(indiceItem);
            agregarLog(entrenador.getNombre() + " usó " + item.getNombre());
        }
    }

    private void verificarFinBatalla() {
        if (jugador1.estaDerrotado() || jugador2.estaDerrotado()) {
            String ganador = jugador1.estaDerrotado() ? jugador2.getNombre() : jugador1.getNombre();
            agregarLog("\n¡" + ganador + " ha ganado la batalla!");
        }
    }

    public boolean isTurnoDe(Entrenador entrenador) {
        return (turnoJugador1 && entrenador == jugador1) ||
                (!turnoJugador1 && entrenador == jugador2);
    }

    public int calcularDanio(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        double efectividad = Tipo.calcularEfectividad(movimiento.getTipo(), defensor.getTipo());
        int nivel = atacante.getNivel();
        int potencia = movimiento.getPotencia();
        int ataque, defensa;

        if (movimiento.getClase().equals("Físico")) {
            ataque = atacante.getAtaque();
            defensa = defensor.getDefensa();
        } else { // Especial
            ataque = atacante.getAtaqueEspecial();
            defensa = defensor.getDefensaEspecial();
        }

        // Fórmula simplificada de daño
        int danio = (int) ((((2 * nivel / 5 + 2) * potencia * ataque / defensa) / 50 + 2) * efectividad);

        return Math.max(1, danio); // Mínimo 1 de daño
    }

    private void cambiarPokemonAutomatico(Entrenador entrenador) {
        for (int i = 0; i < entrenador.getEquipo().size(); i++) {
            Pokemon pokemon = entrenador.getEquipo().get(i);
            if (!pokemon.estaDebilitado()) {
                entrenador.cambiarPokemonActivo(i);
                agregarLog(entrenador.getNombre() + " ha cambiado a " + pokemon.getNombre() + " automáticamente\n");
                break;
            }
        }
    }

    private void agregarLog(String mensaje) {
        logBatalla.append(mensaje);
    }

    public String getLogBatalla() {
        return logBatalla.toString();
    }

    // Getters
    public Entrenador getJugador1() { return jugador1; }
    public Entrenador getJugador2() { return jugador2; }
    public boolean isTurnoJugador1() { return turnoJugador1; }
    public ModoJuego getModo() { return modo; }
}
