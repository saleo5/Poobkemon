package presentacion;

import dominio.exceptions.ModoNoDisponibleException;
import javax.swing.*;
import java.awt.*;
import dominio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeleccionModoPanel extends JPanel {
    private JButton btnNormal, btnSupervivencia;
    private MainWindow mainWindow;
    private ModoJuego modoJuego;

    public SeleccionModoPanel(MainWindow mainWindow, ModoJuego modoJuego) {
        this.mainWindow = mainWindow;
        this.modoJuego = modoJuego;
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Selecciona el modo de juego", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        btnNormal = new JButton("Modo Normal");
        btnSupervivencia = new JButton("Modo Supervivencia");

        btnNormal.addActionListener(e -> mainWindow.mostrarSeleccionPokemon(modoJuego, false));
        btnSupervivencia.addActionListener(e -> {
            try {
                throw new ModoNoDisponibleException("Modo en desarrollo");
            } catch (ModoNoDisponibleException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(lblTitulo);
        add(btnNormal);
        add(btnSupervivencia);
    }
}
