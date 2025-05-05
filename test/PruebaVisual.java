package test;

import dominio.*;
import dominio.pokemon.*;
import dominio.items.*;
import presentacion.BatallaPanel;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PruebaVisual {
    private static int pruebaActual = 0;
    private static JFrame frameActual;
    private static Timer timer;

    public static void main(String[] args) {
        ejecutarSiguientePrueba();
    }

    private static void ejecutarSiguientePrueba() {
        // Cerrar ventana anterior si existe
        if (frameActual != null) {
            frameActual.dispose();
        }

        // Detener timer anterior si existe
        if (timer != null) {
            timer.stop();
        }

        switch (pruebaActual) {
            case 0:
                pruebaBasica();
                break;
            case 1:
                pruebaCambioPokemonYItems();
                break;
            case 2:
                pruebaBatallaCompleta();
                break;
            default:
                System.out.println("Todas las pruebas completadas");
                System.exit(0);
                return;
        }

        pruebaActual++;
    }

    private static void pruebaBasica() {
        List<Pokemon> equipoJugador = List.of(
                FabricaPokemon.crearCharizard(),
                FabricaPokemon.crearVenusaur()
        );

        List<Pokemon> equipoOponente = List.of(
                FabricaPokemon.crearBlastoise(),
                FabricaPokemon.crearGengar()
        );

        List<Item> items = List.of(
                new Potion(),
                new SuperPotion()
        );

        Juego juego = new Juego(ModoJuego.PVS_P, "Ash", "Misty",
                equipoJugador, equipoOponente,
                items, items);

        frameActual = new JFrame("Prueba Básica - Ataque Simple");
        frameActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BatallaPanel batallaPanel = new BatallaPanel(null, juego);
        frameActual.add(batallaPanel);
        frameActual.pack();
        frameActual.setVisible(true);

        timer = new Timer(2000, e -> {
            juego.ejecutarTurno(0, 0); // Ataque básico
            batallaPanel.actualizarInterfaz();

            // Esperar 3 segundos y pasar a la siguiente prueba
            new Timer(3000, ev -> ejecutarSiguientePrueba()).start();
        });
        timer.start();
    }

    private static void pruebaCambioPokemonYItems() {
        List<Pokemon> equipoJugador = List.of(
                FabricaPokemon.crearCharizard(),
                FabricaPokemon.crearVenusaur(),
                FabricaPokemon.crearBlastoise()
        );

        List<Item> items = List.of(
                new Potion(),
                new SuperPotion(),
                new Potion()
        );

        Juego juego = new Juego(ModoJuego.PVS_P, "Ash", "Gary",
                equipoJugador, equipoJugador,
                items, items);

        frameActual = new JFrame("Prueba Cambio Pokémon + Ítems");
        frameActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BatallaPanel batallaPanel = new BatallaPanel(null, juego);
        frameActual.add(batallaPanel);
        frameActual.pack();
        frameActual.setVisible(true);

        timer = new Timer(20000, e -> {
            // Secuencia de pruebas
            juego.ejecutarTurno(0, 0); // Ataque
            batallaPanel.actualizarInterfaz();

            new Timer(20000, ev -> {
                juego.ejecutarTurno(1, 1); // Cambio
                batallaPanel.actualizarInterfaz();

                new Timer(20000, evt -> {
                    juego.ejecutarTurno(2, 0); // Ítem
                    batallaPanel.actualizarInterfaz();

                    new Timer(20000, evtt -> {
                        juego.ejecutarTurno(2, 1); // SuperPotion
                        batallaPanel.actualizarInterfaz();

                        // Pasar a siguiente prueba después de 3 segundos
                        new Timer(3000, evttt -> ejecutarSiguientePrueba()).start();
                    }).start();
                }).start();
            }).start();

            // Solo ejecutar esta secuencia una vez
            ((Timer)e.getSource()).stop();
        });
        timer.start();
    }

    private static void pruebaBatallaCompleta() {
        Pokemon pikachu = new Pokemon("Pikachu", "Eléctrico", 50, 100, 55, 40, 50, 90, 48) {
            @Override public boolean puedeAprender(Movimiento m) { return true; }
        };

        Pokemon squirtle = new Pokemon("Squirtle", "Agua", 50, 100, 48, 65, 50, 43, 26) {
            @Override public boolean puedeAprender(Movimiento m) { return true; }
        };

        List<Pokemon> equipo1 = List.of(pikachu);
        List<Pokemon> equipo2 = List.of(squirtle);

        Juego juego = new Juego(ModoJuego.PVS_P, "Entrenador A", "Entrenador B",
                equipo1, equipo2, new ArrayList<>(), new ArrayList<>());

        pikachu.aprenderMovimiento(new Movimiento("Impactrueno", "Eléctrico", 40, 100, 30, "Especial"), 0);
        squirtle.aprenderMovimiento(new Movimiento("Burbuja", "Agua", 40, 100, 30, "Especial"), 0);

        frameActual = new JFrame("Prueba Batalla Completa");
        frameActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BatallaPanel batallaPanel = new BatallaPanel(null, juego);
        frameActual.add(batallaPanel);
        frameActual.pack();
        frameActual.setVisible(true);

        timer = new Timer(15000, e -> {
            if (!juego.getJugador1().estaDerrotado() && !juego.getJugador2().estaDerrotado()) {
                if (juego.isTurnoJugador1()) {
                    juego.ejecutarTurno(0, 0); // Jugador 1 ataca
                } else {
                    juego.ejecutarTurno(0, 0); // Jugador 2 ataca
                }
                batallaPanel.actualizarInterfaz();
            } else {
                // Fin de la batalla, pasar a siguiente prueba después de 3 segundos
                new Timer(3000, ev -> ejecutarSiguientePrueba()).start();
                ((Timer)e.getSource()).stop();
            }
        });
        timer.start();
    }
}
