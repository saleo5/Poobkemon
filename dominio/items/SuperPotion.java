package dominio.items;

import dominio.pokemon.Pokemon;

public class SuperPotion extends Item {
    public SuperPotion() {
        super("SuperPotion", "Recupera 50 PS", "ps 50");
    }

    @Override
    public void usar(Pokemon pokemon) {
        if (pokemon.getPs() > 0) { // No se puede usar en pokémon debilitados
            int nuevaVida = Math.min(pokemon.getPsMax(), pokemon.getPs() + 50);
            pokemon.setPs(nuevaVida);
        }
    }
}
