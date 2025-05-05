package dominio;

import java.util.ArrayList;
import java.util.List;
import dominio.items.*;
import dominio.pokemon.Pokemon;

public class Entrenador {
    private String nombre;
    private List<Pokemon> equipo;
    private List<Item> mochila;
    private int pokemonActivoIndex;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
        this.mochila = new ArrayList<>();
        this.pokemonActivoIndex = 0;
    }

    // Métodos para manejar Pokémon
    public void agregarPokemon(Pokemon pokemon) {
        equipo.add(pokemon);
    }

    public Pokemon getPokemonActivo() {
        if (equipo.isEmpty()) return null;
        return equipo.get(pokemonActivoIndex);
    }

    public void cambiarPokemonActivo(int index) {
        if (index >= 0 && index < equipo.size()) {
            pokemonActivoIndex = index;
        }
    }

    public List<Pokemon> getEquipo() {
        return new ArrayList<>(equipo);
    }

    // Métodos para manejar ítems
    public void agregarItem(Item item) {
        mochila.add(item);
    }

    public void removerItem(int index) {
        if (index >= 0 && index < mochila.size()) {
            mochila.remove(index);
        }
    }

    public List<Item> getMochila() {
        return new ArrayList<>(mochila);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    // Verificar si el entrenador ha perdido
    public boolean estaDerrotado() {
        for (Pokemon pokemon : equipo) {
            if (!pokemon.estaDebilitado()) {
                return false;
            }
        }
        return true;
    }
}
