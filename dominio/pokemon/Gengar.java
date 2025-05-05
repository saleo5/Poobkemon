package dominio.pokemon;

import dominio.Movimiento;
import dominio.pokemon.Pokemon;

public class Gengar extends Pokemon {
    public Gengar() {
        super("Gengar", "Fantasma/Veneno", 100, 360, 293, 280, 348, 295, 328);
    }

    @Override
    public boolean puedeAprender(Movimiento movimiento) {
        return movimiento.getTipo().equals("Veneno") ||
                movimiento.getTipo().equals("Fantasma") ||
                movimiento.getTipo().equals("Siniestro") ||
                movimiento.getTipo().equals("Normal");
    }
}
