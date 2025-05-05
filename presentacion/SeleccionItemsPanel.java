package presentacion;

import dominio.ModoJuego;
import dominio.items.*;
import presentacion.componentes.ItemCard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import dominio.pokemon.Pokemon;

public class SeleccionItemsPanel extends JPanel {
    private JButton btnSiguiente;
    private List<ItemCard> cards;
    private MainWindow mainWindow;
    private boolean esJugador1;
    private ModoJuego modoJuego;

    public SeleccionItemsPanel(MainWindow mainWindow, boolean esJugador1) {
        this.mainWindow = mainWindow;
        this.esJugador1 = esJugador1;
        this.modoJuego = mainWindow.getModoJuego();

        setLayout(new BorderLayout());

        // Título del panel
        JLabel lblTitulo = new JLabel((esJugador1 ? "Jugador 1" : "Jugador 2") + " - Selecciona tus Ítems",
                SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Panel de instrucciones
        JPanel instruccionesPanel = new JPanel();
        JLabel lblInstrucciones = new JLabel("Puedes seleccionar hasta 2 Pociones y 1 SuperPoción");
        instruccionesPanel.add(lblInstrucciones);

        // Panel principal de ítems
        JPanel itemsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        cards = new ArrayList<>();

        // Crear cards para los ítems disponibles
        cards.add(new ItemCard(new Potion(), "Poción - Recupera 20 PS"));
        cards.add(new ItemCard(new Potion(), "Poción - Recupera 20 PS"));
        cards.add(new ItemCard(new SuperPotion(), "SuperPoción - Recupera 50 PS"));

        for (ItemCard card : cards) {
            itemsPanel.add(card);
        }

        // Configurar botón Siguiente
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarSeleccionItems();
            }
        });

        // Organizar componentes
        add(lblTitulo, BorderLayout.NORTH);
        add(instruccionesPanel, BorderLayout.CENTER);
        add(itemsPanel, BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        panelSur.add(btnSiguiente);
        add(panelSur, BorderLayout.SOUTH);
    }

    private void procesarSeleccionItems() {
        List<Item> seleccionados = new ArrayList<>();
        int pociones = 0, superPociones = 0;

        // Recoger los ítems seleccionados
        for (ItemCard card : cards) {
            if (card.isSelected()) {
                Item item = card.getItem();
                seleccionados.add(item);

                if (item instanceof Potion) {
                    pociones++;
                } else if (item instanceof SuperPotion) {
                    superPociones++;
                }
            }
        }

        // Validar límites de ítems
        if (pociones > 2 || superPociones > 1) {
            JOptionPane.showMessageDialog(this,
                    "Límite excedido: Máximo 2 Pociones y 1 SuperPoción",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Guardar selección
        if (esJugador1) {
            mainWindow.setItemsJugador1(seleccionados);

            if (modoJuego == ModoJuego.PVS_P) {
                // Verificar si el jugador 2 ya tiene Pokémon seleccionados
                if (mainWindow.getPokemonesJugador2().isEmpty()) {
                    // Si no tiene Pokémon, mostrar selección de Pokémon para jugador 2
                    mainWindow.mostrarSeleccionPokemon(modoJuego, false);
                } else {
                    // Si ya tiene Pokémon, mostrar selección de ítems para jugador 2
                    mainWindow.mostrarSeleccionItems(false);
                }
            } else {
                // Para modos no PvP, ir directamente a la batalla
                mainWindow.iniciarBatalla();
            }
        } else {
            // Selección de ítems del jugador 2 completada
            mainWindow.setItemsJugador2(seleccionados);
            mainWindow.iniciarBatalla();
        }
    }

}
