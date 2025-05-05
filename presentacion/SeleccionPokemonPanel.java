package presentacion;

import dominio.FabricaPokemon;
import dominio.ModoJuego;
import dominio.pokemon.*;
import presentacion.componentes.PokemonCard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SeleccionPokemonPanel extends JPanel {
    private JButton btnSiguiente;
    private List<PokemonCard> cards;
    private MainWindow mainWindow;
    private ModoJuego modoJuego;
    private boolean esJugador1;

    public SeleccionPokemonPanel(MainWindow mainWindow, ModoJuego modoJuego, boolean esJugador1) {
        this.mainWindow = mainWindow;
        this.modoJuego = modoJuego;
        this.esJugador1 = esJugador1;

        setLayout(new BorderLayout());

        // Título del panel
        JLabel lblTitulo = new JLabel((esJugador1 ? "Jugador 1" : "Jugador 2") + " - Selecciona tus Pokémon",
                SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Panel principal para las cartas de Pokémon
        JPanel pokemonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        cards = new ArrayList<>();

        // Crear cards para los 4 pokémones disponibles
        cards.add(new PokemonCard(FabricaPokemon.crearCharizard()));
        cards.add(new PokemonCard(FabricaPokemon.crearBlastoise()));
        cards.add(new PokemonCard(FabricaPokemon.crearVenusaur()));
        cards.add(new PokemonCard(FabricaPokemon.crearGengar()));

        for (PokemonCard card : cards) {
            pokemonPanel.add(card);
        }

        // Configurar botón Siguiente
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarSeleccionPokemon();
            }
        });

        // Organizar componentes
        add(lblTitulo, BorderLayout.NORTH);
        add(pokemonPanel, BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        panelSur.add(btnSiguiente);
        add(panelSur, BorderLayout.SOUTH);
    }

    private void procesarSeleccionPokemon() {
        List<Pokemon> seleccionados = new ArrayList<>();

        for (PokemonCard card : cards) {
            if (card.isSelected()) {
                seleccionados.add(card.getPokemon());
            }
        }

        if (seleccionados.size() != 4) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar exactamente 4 Pokémon",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (esJugador1) {
            mainWindow.setPokemonesJugador1(seleccionados);

            if (modoJuego == ModoJuego.PVS_P) {
                // Solo mostrar selección para jugador 2 si es PvP
                mainWindow.mostrarSeleccionPokemon(modoJuego, false);
            } else {
                // Para otros modos, el jugador 2 usa Pokémon predefinidos
                mainWindow.setPokemonesJugador2(crearEquipoPredefinido());
                mainWindow.mostrarSeleccionItems(true);
            }
        } else {
            mainWindow.setPokemonesJugador2(seleccionados);
            mainWindow.mostrarSeleccionItems(true);
        }
    }

    private List<Pokemon> crearEquipoPredefinido() {
        return FabricaPokemon.crearEquipoPredefinido();
    }
}
