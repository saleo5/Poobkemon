package dominio;

public class Movimiento {
    private String nombre;
    private String tipo;
    private int potencia;
    private int precision;
    private int pp; // Puntos de poder
    private String clase; // Físico, Especial o Estado

    public Movimiento(String nombre, String tipo, int potencia, int precision, int pp, String clase) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.potencia = potencia;
        this.precision = precision;
        this.pp = pp;
        this.clase = clase;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getPotencia() { return potencia; }
    public int getPrecision() { return precision; }
    public int getPp() { return pp; }
    public String getClase() { return clase; }

    public void reducirPp() {
        if (pp > 0) {
            pp--;
        }
    }
}
