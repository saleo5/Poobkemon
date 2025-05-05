package dominio;

public class Tipo {
    public static double calcularEfectividad(String tipoAtaque, String tipoDefensa) {
        // Tabla de efectividad simplificada
        switch (tipoAtaque) {
            case "Fuego":
                if (tipoDefensa.equals("Planta")) return 2.0;
                if (tipoDefensa.equals("Agua") || tipoDefensa.equals("Fuego")) return 0.5;
                break;
            case "Agua":
                if (tipoDefensa.equals("Fuego")) return 2.0;
                if (tipoDefensa.equals("Planta") || tipoDefensa.equals("Agua")) return 0.5;
                break;
            case "Planta":
                if (tipoDefensa.equals("Agua")) return 2.0;
                if (tipoDefensa.equals("Fuego") || tipoDefensa.equals("Planta")) return 0.5;
                break;
            case "Eléctrico":
                if (tipoDefensa.equals("Agua")) return 2.0;
                if (tipoDefensa.equals("Planta")) return 0.5;
                break;
        }
        return 1.0;
    }
}
