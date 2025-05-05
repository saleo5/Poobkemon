package dominio.pokemon;

import dominio.Movimiento;

public class Charizard extends Pokemon {
    public Charizard() {
        super("Charizard", "Fuego", 100, 360, 293, 280, 348, 295, 328);
    }

    @Override
    public boolean puedeAprender(Movimiento movimiento) {
        return movimiento.getTipo().equals("Fuego") ||
                movimiento.getTipo().equals("Volador") ||
                movimiento.getTipo().equals("Dragón") ||
                movimiento.getTipo().equals("Normal");
    }
}
