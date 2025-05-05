package dominio.pokemon;

import dominio.pokemon.Pokemon;
import dominio.Movimiento;

public class Blastoise extends Pokemon {
    public Blastoise() {
        super("Blastoise", "Agua", 100, 362, 291, 328, 295, 339, 280);
    }

    @Override
    public boolean puedeAprender(Movimiento movimiento) {
        // Blastoise puede aprender movimientos de Agua, Hielo y algunos Normal
        return movimiento.getTipo().equals("Agua") ||
                movimiento.getTipo().equals("Hielo") ||
                movimiento.getTipo().equals("Normal");
    }
}
