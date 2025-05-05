package dominio.items;

import dominio.pokemon.Pokemon;

public class Item {
    private String nombre;
    private String descripcion;
    private String efecto;

    public Item(String nombre, String descripcion, String efecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.efecto = efecto;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getEfecto() { return efecto; }

    public void usar(Pokemon pokemon) {
        // Implementar efecto del ítem según su tipo
        if (nombre.equals("Potion")) {
            pokemon.setPs(pokemon.getPs() + 20);
        } else if (nombre.equals("SuperPotion")) {
            pokemon.setPs(pokemon.getPs() + 50);
        }
    }
}
