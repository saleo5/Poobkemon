package dominio;

import dominio.items.Item;

public class FabricaItems {
    public static Item crearPotion() {
        return new Item("Potion", "Recupera 20 PS", "ps 20");
    }

    public static Item crearSuperPotion() {
        return new Item("SuperPotion", "Recupera 50 PS", "ps 50");
    }
}
