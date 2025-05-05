package dominio.pokemon;

import dominio.Movimiento;

public abstract class Pokemon {
    private String nombre;
    private String tipo;
    private int nivel;
    private int ps; // Puntos de salud actuales
    private int psMax; // Puntos de salud máximos
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;
    private Movimiento[] movimientos;

    public Pokemon(String nombre, String tipo, int nivel, int psMax, int ataque,
                   int defensa, int ataqueEspecial, int defensaEspecial, int velocidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.psMax = psMax;
        this.ps = psMax; // Al crear, los PS actuales son los máximos
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.movimientos = new Movimiento[4]; // Cada pokemon tiene 4 movimientos
    }

    // Método para obtener los PS máximos
    public int getPsMax() {
        return psMax;
    }

    // Método para curar completamente al Pokémon
    public void curarCompletamente() {
        this.ps = this.psMax;
    }

    // Modificación del método recibirDanio para asegurar que no sea negativo
    public void recibirDanio(int danio) {
        this.ps = Math.max(0, this.ps - danio);
    }

    // Modificación del setter de PS para no exceder el máximo
    public void setPs(int ps) {
        this.ps = Math.min(psMax, Math.max(0, ps));
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getNivel() { return nivel; }
    public int getPs() { return ps; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAtaqueEspecial() { return ataqueEspecial; }
    public int getDefensaEspecial() { return defensaEspecial; }
    public int getVelocidad() { return velocidad; }

    public void aprenderMovimiento(Movimiento movimiento, int indice) {
        if (indice >= 0 && indice < 4) {
            movimientos[indice] = movimiento;
        }
    }

    public Movimiento getMovimiento(int indice) {
        if (indice >= 0 && indice < 4) {
            return movimientos[indice];
        }
        return null;
    }

    // Método para verificar si el Pokémon está debilitado
    public boolean estaDebilitado() {
        return ps <= 0;
    }

    public abstract boolean puedeAprender(Movimiento movimiento);
}
