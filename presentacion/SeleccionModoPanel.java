package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dominio.*;
import dominio.exceptions.ModoNoDisponibleException;

public class SeleccionModoPanel extends JPanel {
    private JButton btnNormal, btnSupervivencia;
    private MainWindow mainWindow;

    public SeleccionModoPanel(MainWindow mainWindow, ModoJuego modoJuego) {
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());  // Usamos BorderLayout

        // Crear un panel para la imagen de fondo
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de fondo
                ImageIcon icon = new ImageIcon(getClass().getResource("/resources/02.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null); // Ajustar la imagen al tamaño del panel
            }
        };
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS)); // Usar BoxLayout para mejor manejo de los botones

        // Crear los botones
        btnNormal = new JButton("Modo Normal");
        btnSupervivencia = new JButton("Modo Supervivencia");

        // Cambiar el color del texto de los botones a negro
        btnNormal.setForeground(Color.BLACK);
        btnSupervivencia.setForeground(Color.BLACK);

        // Aumentar el tamaño de la fuente
        Font largeFont = new Font("Arial", Font.BOLD, 24); // Fuente más grande
        btnNormal.setFont(largeFont);
        btnSupervivencia.setFont(largeFont);

        // Hacer los botones transparentes
        btnNormal.setContentAreaFilled(true);  // Eliminar el fondo
        btnNormal.setBorderPainted(true);      // Eliminar el borde
        btnNormal.setFocusPainted(false);       // Eliminar el foco

        btnSupervivencia.setContentAreaFilled(true);  // Eliminar el fondo
        btnSupervivencia.setBorderPainted(true);      // Eliminar el borde
        btnSupervivencia.setFocusPainted(false);       // Eliminar el foco

        // Añadir acción a los botones
        btnNormal.addActionListener(e -> mainWindow.mostrarSeleccionPokemon(modoJuego, true));
        btnSupervivencia.addActionListener(e -> {
            try {
                throw new ModoNoDisponibleException("Modo en desarrollo");
            } catch (ModoNoDisponibleException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50)); // Para que estén uno al lado del otro con separación
        buttonPanel.setOpaque(false); // Asegurar que el panel de los botones sea transparente

        // Añadir los botones al panel de los botones
        buttonPanel.add(btnNormal);
        buttonPanel.add(btnSupervivencia);

        // Añadir el panel de botones al panel de la imagen
        imagePanel.add(Box.createVerticalGlue()); // Añadir espacio flexible para mover los botones hacia el centro
        imagePanel.add(buttonPanel);
        imagePanel.add(Box.createVerticalGlue()); // Añadir espacio flexible para mover los botones hacia el centro

        // Organizar los componentes y añadir al panel principal
        add(imagePanel, BorderLayout.CENTER);
    }
}
