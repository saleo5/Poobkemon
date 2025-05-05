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
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblTitulo = new JLabel("POOBkemon", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));

        btnPvsP = new JButton("Player vs Player");
        btnPvsM = new JButton("Player vs Machine");
        btnMvsM = new JButton("Machine vs Machine");

        btnPvsP.addActionListener(e -> mainWindow.mostrarSeleccionModo(ModoJuego.PVS_P));
        btnPvsM.addActionListener(e -> mainWindow.mostrarSeleccionModo(ModoJuego.PVS_M));
        btnMvsM.addActionListener(e -> mainWindow.mostrarSeleccionModo(ModoJuego.MVS_M));

        add(lblTitulo);
        add(btnPvsP);
        add(btnPvsM);
        add(btnMvsM);
    }
}
