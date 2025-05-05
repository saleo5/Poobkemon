package presentacion.componentes;

import dominio.pokemon.Pokemon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PokemonCard extends JPanel {
    private Pokemon pokemon;
    private JCheckBox checkBox;

    public PokemonCard(Pokemon pokemon) {
        this.pokemon = pokemon;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GREEN));
        setPreferredSize(new Dimension(180, 220));

        // Panel superior con la imagen del Pokémon
        JPanel imagePanel = new JPanel();

        try {
            ImageIcon originalIcon = new ImageIcon("resources/pokemons/" +
                    pokemon.getNombre().toLowerCase() + ".png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            // Si no hay imagen, mostrar el nombre
            imagePanel.add(new JLabel(pokemon.getNombre()));
        }

        // Panel central con información del Pokémon
        JPanel infoPanel = new JPanel(new GridLayout(5, 1)); // Aumentado a 5 filas
        infoPanel.add(new JLabel("Nombre: " + pokemon.getNombre(), SwingConstants.CENTER));
        infoPanel.add(new JLabel("Tipo: " + pokemon.getTipo(), SwingConstants.CENTER));
        infoPanel.add(new JLabel("PS: " + pokemon.getPs() + "/" + pokemon.getPsMax(), SwingConstants.CENTER));
        infoPanel.add(new JLabel("Ataque: " + pokemon.getAtaque(), SwingConstants.CENTER));
        infoPanel.add(new JLabel("Defensa: " + pokemon.getDefensa(), SwingConstants.CENTER));

        // Checkbox para selección
        checkBox = new JCheckBox("Seleccionar");
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setBackground(e.getStateChange() == ItemEvent.SELECTED ?
                        new Color(200, 255, 200) : Color.WHITE);
            }
        });

        // Organizar componentes
        add(imagePanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(checkBox, BorderLayout.SOUTH);
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setSelected(boolean selected) {
        checkBox.setSelected(selected);
        setBackground(selected ? new Color(200, 255, 200) : Color.WHITE);
    }
}
