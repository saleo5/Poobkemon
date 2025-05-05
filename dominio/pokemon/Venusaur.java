package dominio.pokemon;

import dominio.Movimiento;
import dominio.pokemon.Pokemon;

public class Venusaur extends Pokemon {
    public Venusaur() {
        super("Venusaur", "Planta", 100, 360, 293, 280, 348, 295, 328);
    }

    @Override
    public boolean puedeAprender(Movimiento movimiento) {
        return movimiento.getTipo().equals("Planta") ||
                movimiento.getTipo().equals("Veneno") ||
                movimiento.getTipo().equals("Normal");
    }
}
