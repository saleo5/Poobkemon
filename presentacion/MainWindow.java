package presentacion;

import dominio.*;
import dominio.pokemon.*;
import dominio.items.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private MenuPanel menuPanel;
    private SeleccionModoPanel seleccionModoPanel;
    private SeleccionPokemonPanel seleccionPokemonPanel;
    private SeleccionItemsPanel seleccionItemsPanel;
    private BatallaPanel batallaPanel;
    private ModoJuego modoJuego;

    private List<Pokemon> pokemonesJugador1 = new ArrayList<>();
    private List<Pokemon> pokemonesJugador2 = new ArrayList<>();
    private List<Item> itemsJugador1 = new ArrayList<>();
    private List<Item> itemsJugador2 = new ArrayList<>();

    public MainWindow() {
        setTitle("POOBkemon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mostrarMenu();
        setVisible(true);
    }

    public void mostrarMenu() {
        getContentPane().removeAll();
        menuPanel = new MenuPanel(this);
        add(menuPanel);
        revalidate();
        repaint();
    }

    public void mostrarSeleccionModo(ModoJuego modoJuego) {
        getContentPane().removeAll();
        seleccionModoPanel = new SeleccionModoPanel(this, modoJuego);
        add(seleccionModoPanel);
        revalidate();
        repaint();
    }

    public void mostrarSeleccionPokemon(ModoJuego modoJuego, boolean esJugador1) {
        this.modoJuego = modoJuego;
        getContentPane().removeAll();
        seleccionPokemonPanel = new SeleccionPokemonPanel(this, modoJuego, esJugador1);
        add(seleccionPokemonPanel);
        revalidate();
        repaint();
    }

    public void mostrarSeleccionItems(boolean esJugador1) {
        getContentPane().removeAll();
        seleccionItemsPanel = new SeleccionItemsPanel(this, esJugador1);
        add(seleccionItemsPanel);
        revalidate();
        repaint();
    }

    // Método para iniciar batalla
    public void iniciarBatalla() {
        // Validar ambos equipos antes de iniciar
        if (pokemonesJugador1 == null || pokemonesJugador1.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No se seleccionaron Pokémon para el Jugador 1",
                    "Error", JOptionPane.ERROR_MESSAGE);
            mostrarSeleccionPokemon(modoJuego, true);
            return;
        }

        if ((modoJuego == ModoJuego.PVS_P) &&
                (pokemonesJugador2 == null || pokemonesJugador2.isEmpty())) {
            JOptionPane.showMessageDialog(this,
                    "No se seleccionaron Pokémon para el Jugador 2",
                    "Error", JOptionPane.ERROR_MESSAGE);
            mostrarSeleccionPokemon(modoJuego, false);
            return;
        }

        // Si es PvM o MvM y no hay jugador 2, crear equipo automático
        if (pokemonesJugador2 == null || pokemonesJugador2.isEmpty()) {
            pokemonesJugador2 = crearEquipoPredefinido();
        }

        getContentPane().removeAll();
        Juego juego = new Juego(modoJuego, "Jugador 1",
                modoJuego == ModoJuego.PVS_P ? "Jugador 2" : "Oponente",
                pokemonesJugador1, pokemonesJugador2,
                itemsJugador1, itemsJugador2);
        batallaPanel = new BatallaPanel(this, juego);
        add(batallaPanel);
        revalidate();
        repaint();
    }

    private List<Pokemon> crearEquipoPredefinido() {
        return FabricaPokemon.crearEquipoPredefinido();
    }

    // Métodos para asignar Pokémon
    public void setPokemonesJugador1(List<Pokemon> pokemones) {
        this.pokemonesJugador1 = pokemones != null ? new ArrayList<>(pokemones) : new ArrayList<>();
    }

    public void setPokemonesJugador2(List<Pokemon> pokemones) {
        this.pokemonesJugador2 = pokemones != null ? new ArrayList<>(pokemones) : new ArrayList<>();
    }

    public void setItemsJugador1(List<Item> items) {
        this.itemsJugador1 = items;
    }

    public void setItemsJugador2(List<Item> items) {
        this.itemsJugador2 = items;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }

    public ModoJuego getModoJuego() {
        return this.modoJuego;
    }

    public List<Pokemon> getPokemonesJugador2() {
        return pokemonesJugador2 != null ? pokemonesJugador2 : new ArrayList<>();
    }

    public List<Pokemon> getPokemonesJugador1() {
        return new ArrayList<>(pokemonesJugador1); // Devuelve una copia para evitar modificaciones externas
    }

}
