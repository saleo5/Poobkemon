package presentacion;

import dominio.*;
import presentacion.componentes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dominio.pokemon.*;
import dominio.items.*;

public class BatallaPanel extends JPanel {
    private Juego juego;
    private MainWindow mainWindow;

    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JPanel panelInferior;
    private HealthBar healthBarJugador;
    private HealthBar healthBarOponente;
    private JTextArea txtLog;
    private JPanel panelOpciones;

    public BatallaPanel(MainWindow mainWindow, Juego juego) {
        this.mainWindow = mainWindow;
        this.juego = juego;
        setLayout(new BorderLayout());

        crearPanelSuperior();
        crearPanelCentral();
        crearPanelInferior();

        actualizarInterfaz();
    }

    private void crearPanelSuperior() {
        panelSuperior = new JPanel(new GridLayout(1, 2));

        // Panel jugador
        JPanel panelJugador = new JPanel(new BorderLayout());
        Pokemon pokemonJugador = juego.getJugador1().getPokemonActivo();
        JLabel lblPokemonJugador = new JLabel(pokemonJugador.getNombre(), SwingConstants.CENTER);
        healthBarJugador = new HealthBar(pokemonJugador.getPsMax());
        panelJugador.add(lblPokemonJugador, BorderLayout.NORTH);
        panelJugador.add(healthBarJugador, BorderLayout.CENTER);

        // Panel oponente
        JPanel panelOponente = new JPanel(new BorderLayout());
        Pokemon pokemonOponente = juego.getJugador2().getPokemonActivo();
        JLabel lblPokemonOponente = new JLabel(pokemonOponente.getNombre(), SwingConstants.CENTER);
        healthBarOponente = new HealthBar(pokemonOponente.getPsMax());
        panelOponente.add(lblPokemonOponente, BorderLayout.NORTH);
        panelOponente.add(healthBarOponente, BorderLayout.CENTER);

        panelSuperior.add(panelJugador);
        panelSuperior.add(panelOponente);
        add(panelSuperior, BorderLayout.NORTH);
    }

    private void crearPanelCentral() {
        panelCentral = new JPanel(new BorderLayout());
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        panelCentral.add(new JScrollPane(txtLog), BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);
    }

    private void crearPanelInferior() {
        panelInferior = new JPanel(new BorderLayout());
        panelOpciones = new JPanel(new BorderLayout());

        panelInferior.add(panelOpciones, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        prepararInterfazParaTurno();
    }

    private void prepararInterfazParaTurno() {
        panelOpciones.removeAll();

        JPanel panelBotones = new JPanel(new GridLayout(1, 4));

        JButton btnAtacar = new JButton("Atacar");
        JButton btnCambiar = new JButton("Cambiar");
        JButton btnItem = new JButton("Ítem");
        JButton btnHuir = new JButton("Huir");

        btnAtacar.addActionListener(e -> mostrarOpcionesAtaque());
        btnCambiar.addActionListener(e -> mostrarOpcionesCambio());
        btnItem.addActionListener(e -> mostrarOpcionesItems());
        btnHuir.addActionListener(e -> huir());

        // Habilitar botones solo para el jugador activo
        boolean esTurnoJugador1 = juego.isTurnoJugador1();
        btnAtacar.setEnabled(esTurnoJugador1 || juego.getModo() == ModoJuego.PVS_P);
        btnCambiar.setEnabled(esTurnoJugador1 || juego.getModo() == ModoJuego.PVS_P);
        btnItem.setEnabled(esTurnoJugador1 || juego.getModo() == ModoJuego.PVS_P);
        btnHuir.setEnabled(esTurnoJugador1);

        panelBotones.add(btnAtacar);
        panelBotones.add(btnCambiar);
        panelBotones.add(btnItem);
        panelBotones.add(btnHuir);

        panelOpciones.add(panelBotones, BorderLayout.CENTER);
        panelOpciones.revalidate();
        panelOpciones.repaint();

        // Si es turno de la máquina (en modos no PvP), ejecutar acción automática
        if (!juego.isTurnoJugador1() && juego.getModo() != ModoJuego.PVS_P) {
            ejecutarTurnoAutomatico();
        }
    }

    private void mostrarOpcionesAtaque() {
        Entrenador jugadorActual = juego.isTurnoJugador1() ? juego.getJugador1() : juego.getJugador2();

        panelOpciones.removeAll();
        panelOpciones.setLayout(new GridLayout(2, 2));

        Pokemon pokemon = jugadorActual.getPokemonActivo();
        for (int i = 0; i < 4; i++) {
            Movimiento movimiento = pokemon.getMovimiento(i);
            if (movimiento != null && movimiento.getPp() > 0) {
                JButton btnMovimiento = new JButton(movimiento.getNombre() + " (PP: " + movimiento.getPp() + ")");
                final int index = i;
                btnMovimiento.addActionListener(e -> {
                    juego.ejecutarTurno(0, index);
                    actualizarInterfaz();
                    prepararInterfazParaTurno();
                });
                panelOpciones.add(btnMovimiento);
            }
        }
        panelOpciones.revalidate();
        panelOpciones.repaint();
    }

    private void mostrarOpcionesCambio() {
        Entrenador jugadorActual = juego.isTurnoJugador1() ? juego.getJugador1() : juego.getJugador2();

        panelOpciones.removeAll();
        panelOpciones.setLayout(new GridLayout(0, 1));

        List<Pokemon> equipo = jugadorActual.getEquipo();
        Pokemon activo = jugadorActual.getPokemonActivo();

        for (int i = 0; i < equipo.size(); i++) {
            Pokemon pokemon = equipo.get(i);
            if (pokemon != activo && !pokemon.estaDebilitado()) {
                JButton btnPokemon = new JButton(pokemon.getNombre() + " (PS: " + pokemon.getPs() + "/" + pokemon.getPsMax() + ")");
                final int index = i;
                btnPokemon.addActionListener(e -> {
                    juego.ejecutarTurno(1, index);
                    actualizarInterfaz();
                    prepararInterfazParaTurno();
                });
                panelOpciones.add(btnPokemon);
            }
        }
        panelOpciones.revalidate();
        panelOpciones.repaint();
    }

    private void mostrarOpcionesItems() {
        Entrenador jugadorActual = juego.isTurnoJugador1() ? juego.getJugador1() : juego.getJugador2();

        panelOpciones.removeAll();
        panelOpciones.setLayout(new GridLayout(0, 1));

        List<Item> items = jugadorActual.getMochila();
        if (items.isEmpty()) {
            panelOpciones.add(new JLabel("No tienes ítems disponibles"));
        } else {
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                JButton btnItem = new JButton(item.getNombre() + ": " + item.getDescripcion());
                final int index = i;
                btnItem.addActionListener(e -> {
                    juego.ejecutarTurno(2, index);
                    actualizarInterfaz();
                    prepararInterfazParaTurno();
                });
                panelOpciones.add(btnItem);
            }
        }
        panelOpciones.revalidate();
        panelOpciones.repaint();
    }

    private void ejecutarTurnoAutomatico() {
        new Timer(1500, e -> {
            Entrenador maquina = juego.getJugador2();
            Pokemon pokemon = maquina.getPokemonActivo();

            // Intenta usar un movimiento aleatorio con PP primero
            for (int i = 0; i < 4; i++) {
                Movimiento m = pokemon.getMovimiento(i);
                if (m != null && m.getPp() > 0) {
                    juego.ejecutarTurno(0, i);
                    break;
                }
            }

            actualizarInterfaz();
            prepararInterfazParaTurno();
            ((Timer)e.getSource()).stop();
        }).start();
    }

    private void huir() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que quieres huir de la batalla?",
                "Confirmar huida", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            mainWindow.mostrarMenu();
        }
    }

    private void actualizarInterfaz() {
        // 1. Obtener referencias a los Pokémon activos
        Pokemon pokemonJugador = juego.getJugador1().getPokemonActivo();
        Pokemon pokemonOponente = juego.getJugador2().getPokemonActivo();

        // 2. Actualizar nombres en los paneles superiores
        JPanel panelJugador = (JPanel) panelSuperior.getComponent(0);
        JPanel panelOponente = (JPanel) panelSuperior.getComponent(1);

        // Obtener los JLabel de nombre (creados en crearPanelSuperior)
        JLabel lblNombreJugador = (JLabel) panelJugador.getComponent(0);
        JLabel lblNombreOponente = (JLabel) panelOponente.getComponent(0);

        lblNombreJugador.setText(pokemonJugador.getNombre());
        lblNombreOponente.setText(pokemonOponente.getNombre());

        // 3. Actualizar barras de salud (sin modificar su lógica)
        healthBarJugador.setHealth(pokemonJugador.getPs());
        healthBarOponente.setHealth(pokemonOponente.getPs());

        // 4. Actualizar log de batalla
        txtLog.setText(juego.getLogBatalla());
        txtLog.setCaretPosition(txtLog.getDocument().getLength());

        // 5. Resaltar turno actual en el log
        if (juego.isTurnoJugador1()) {
            txtLog.append("\n--- TURNO DE " + juego.getJugador1().getNombre().toUpperCase() + " ---\n");
        } else {
            txtLog.append("\n--- TURNO DE " + juego.getJugador2().getNombre().toUpperCase() + " ---\n");
        }

        // 6. Verificar fin de batalla
        if (juego.getJugador1().estaDerrotado() || juego.getJugador2().estaDerrotado()) {
            String ganador = juego.getJugador1().estaDerrotado() ?
                    juego.getJugador2().getNombre() :
                    juego.getJugador1().getNombre();

            txtLog.append("\n¡" + ganador + " ha ganado la batalla!\n");

            // Deshabilitar botones de acción
            Component[] botones = ((JPanel) panelInferior.getComponent(0)).getComponents();
            for (Component boton : botones) {
                if (boton instanceof JButton) {
                    boton.setEnabled(false);
                }
            }
        }

        // 7. Forzar actualización visual
        panelSuperior.revalidate();
        panelSuperior.repaint();
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void agregarLog(String mensaje) {
        txtLog.append(mensaje + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }
}
