package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dominio.*;

public class MenuPanel extends JPanel {
    private JButton btnPvsP, btnPvsM, btnMvsM;
    private MainWindow mainWindow;

    public MenuPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());  // Cambié el layout a BorderLayout

        // Crear un panel para la imagen de fondo
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de fondo
                ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pantallaInicio.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null); // Ajustar la imagen al tamaño del panel
            }
        };
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50)); // Para centrar los botones sobre la imagen

        // Crear los botones
        btnPvsP = new JButton("Player vs Player");
        btnPvsM = new JButton("Player vs Machine");
        btnMvsM = new JButton("Machine vs Machine");

        // Hacer los botones transparentes
        btnPvsP.setContentAreaFilled(false);
        btnPvsP.setBorderPainted(false);
        btnPvsP.setFocusPainted(false);

        btnPvsM.setContentAreaFilled(false);
        btnPvsM.setBorderPainted(false);
        btnPvsM.setFocusPainted(false);

        btnMvsM.setContentAreaFilled(false);
        btnMvsM.setBorderPainted(false);
        btnMvsM.setFocusPainted(false);

        // Cambiar el color del texto de los botones
        btnPvsP.setForeground(Color.WHITE);
        btnPvsM.setForeground(Color.WHITE);
        btnMvsM.setForeground(Color.WHITE);

        // Añadir acción a los botones
        btnPvsP.addActionListener(e -> mainWindow.mostrarSeleccionModo(ModoJuego.PVS_P));
        btnPvsM.addActionListener(e -> mainWindow.mostrarSeleccionModo(ModoJuego.PVS_M));
        btnMvsM.addActionListener(e -> mainWindow.mostrarSeleccionModo(ModoJuego.MVS_M));

        // Añadir los botones al panel de la imagen
        imagePanel.add(btnPvsP);
        imagePanel.add(btnPvsM);
        imagePanel.add(btnMvsM);

        // Organizar los componentes sin título en la parte superior
        add(imagePanel, BorderLayout.CENTER);
    }
}
