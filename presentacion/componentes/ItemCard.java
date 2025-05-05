package presentacion.componentes;

import dominio.items.Item;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemCard extends JPanel {
    private Item item;
    private JCheckBox checkBox;

    public ItemCard(Item item, String descripcion) {
        this.item = item;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Checkbox para selección
        checkBox = new JCheckBox(descripcion);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setBackground(e.getStateChange() == ItemEvent.SELECTED ?
                        new Color(200, 255, 200) : Color.WHITE);
            }
        });

        // Aquí podrías añadir una imagen del ítem si la tienes
        JLabel lblImagen = new JLabel(new ImageIcon("resources/items/" +
                item.getNombre().toLowerCase() + ".png"));

        add(lblImagen, BorderLayout.CENTER);
        add(checkBox, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(150, 180));
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public Item getItem() {
        return item;
    }
}
