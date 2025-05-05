package dominio.items;

import dominio.pokemon.Pokemon;

public class Potion extends Item {
    public Potion() {
        super("Potion", "Recupera 20 PS", "ps 20");
    }

    @Override
    public void usar(Pokemon pokemon) {
        if (pokemon.getPs() > 0) { // No se puede usar en pokémon debilitados
            int nuevaVida = Math.min(pokemon.getPsMax(), pokemon.getPs() + 20);
            pokemon.setPs(nuevaVida);
        }
    }
}
