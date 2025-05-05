package dominio;

public class FabricaMovimientos {
    // Movimientos de Fuego (para Charizard)
    public static Movimiento crearLanzallamas() {
        return new Movimiento(
                "Lanzallamas",
                "Fuego",
                90,    // potencia
                100,   // precisión
                15,    // PP
                "Especial"
        );
    }

    public static Movimiento crearGiroFuego() {
        return new Movimiento(
                "Giro Fuego",
                "Fuego",
                35,    // potencia
                85,    // precisión
                15,    // PP
                "Físico"
        );
    }

    public static Movimiento crearDiaSoleado() {
        return new Movimiento(
                "Día Soleado",
                "Fuego",
                0,     // potencia (movimiento de estado)
                100,   // precisión
                5,     // PP
                "Estado"
        );
    }

    public static Movimiento crearGarraDragon() {
        return new Movimiento(
                "Garra Dragón",
                "Dragón",
                80,    // potencia
                100,   // precisión
                15,    // PP
                "Físico"
        );
    }

    // Movimientos de Agua (para Blastoise)
    public static Movimiento crearHidrobomba() {
        return new Movimiento(
                "Hidrobomba",
                "Agua",
                110,   // potencia
                80,    // precisión
                5,     // PP
                "Especial"
        );
    }

    public static Movimiento crearAcuaCola() {
        return new Movimiento(
                "Acua Cola",
                "Agua",
                90,    // potencia
                90,    // precisión
                10,    // PP
                "Físico"
        );
    }

    public static Movimiento crearRefugio() {
        return new Movimiento(
                "Refugio",
                "Agua",
                0,     // potencia (movimiento de estado)
                100,   // precisión
                5,     // PP
                "Estado"
        );
    }

    public static Movimiento crearCabezazo() {
        return new Movimiento(
                "Cabezazo",
                "Normal",
                70,    // potencia
                100,   // precisión
                15,    // PP
                "Físico"
        );
    }

    // Movimientos de Planta (para Venusaur)
    public static Movimiento crearRayoSolar() {
        return new Movimiento(
                "Rayo Solar",
                "Planta",
                120,   // potencia
                100,   // precisión
                10,    // PP
                "Especial"
        );
    }

    public static Movimiento crearLatigazo() {
        return new Movimiento(
                "Latigazo",
                "Planta",
                45,    // potencia
                100,   // precisión
                25,    // PP
                "Físico"
        );
    }

    public static Movimiento crearSomnifero() {
        return new Movimiento(
                "Somnífero",
                "Planta",
                0,     // potencia (movimiento de estado)
                75,    // precisión
                15,    // PP
                "Estado"
        );
    }

    public static Movimiento crearDrenadoras() {
        return new Movimiento(
                "Drenadoras",
                "Planta",
                20,    // potencia (drena vida)
                100,   // precisión
                10,    // PP
                "Especial"
        );
    }

    // Movimientos de Fantasma/Veneno (para Gengar)
    public static Movimiento crearBolaSombra() {
        return new Movimiento(
                "Bola Sombra",
                "Fantasma",
                80,    // potencia
                100,   // precisión
                15,    // PP
                "Especial"
        );
    }

    public static Movimiento crearLenguetazo() {
        return new Movimiento(
                "Lengüetazo",
                "Fantasma",
                30,    // potencia
                100,   // precisión
                30,    // PP
                "Físico"
        );
    }

    public static Movimiento crearMaldicion() {
        return new Movimiento(
                "Maldición",
                "Fantasma",
                0,     // potencia (movimiento de estado)
                -1,    // precisión (siempre acierta)
                10,    // PP
                "Estado"
        );
    }

    public static Movimiento crearTinieblas() {
        return new Movimiento(
                "Tinieblas",
                "Siniestro",
                0,     // potencia (movimiento de estado)
                100,   // precisión
                15,    // PP
                "Estado"
        );
    }
}
