package dominio;

public enum ModoJuego {
    PVS_P("Player vs Player"),
    PVS_M("Player vs Machine"),
    MVS_M("Machine vs Machine");

    private String descripcion;

    ModoJuego(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
