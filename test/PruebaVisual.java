package test;

import dominio.*;
import dominio.pokemon.*;
import java.util.List;
import dominio.items.*;
import presentacion.BatallaPanel;

import javax.swing.*;

public class PruebaVisual {
    public static void main(String[] args) {
        // Configuración inicial
        List<Pokemon> equipoJugador = List.of(
                FabricaPokemon.crearCharizard(),
                FabricaPokemon.crearVenusaur()
        );

        List<Pokemon> equipoOponente = List.of(
                FabricaPokemon.crearBlastoise(),
                FabricaPokemon.crearGengar()
        );

        List<Item> items = List.of(
                new Potion(),
                new SuperPotion()
        );

        Juego juego = new Juego(ModoJuego.PVS_P, "Ash", "Misty",
                equipoJugador, equipoOponente,
                items, items);

        // Ejecutar prueba visual
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Prueba Visual Batalla");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new BatallaPanel(null, juego));
            frame.pack();
            frame.setVisible(true);

            // Simular ataque después de 2 segundos
            new Timer(2000, e -> {
                juego.ejecutarTurno(0, 0); // Ataque básico
                frame.repaint();
            }).start();
        });
    }
}
