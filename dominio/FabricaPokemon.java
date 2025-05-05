package dominio;

import dominio.pokemon.*;
import dominio.FabricaMovimientos;

import java.util.ArrayList;
import java.util.List;

import static dominio.FabricaMovimientos.*;

public class FabricaPokemon {

    public static Pokemon crearCharizard() {
        Charizard charizard = new Charizard();
        configurarMovimientosCharizard(charizard);
        return charizard;
    }

    public static Pokemon crearBlastoise() {
        Blastoise blastoise = new Blastoise();
        configurarMovimientosBlastoise(blastoise);
        return blastoise;
    }

    public static Pokemon crearVenusaur() {
        Venusaur venusaur = new Venusaur();
        configurarMovimientosVenusaur(venusaur);
        return venusaur;
    }

    private static void configurarMovimientosVenusaur(Venusaur venusaur) {

    }

    public static Pokemon crearGengar() {
        Gengar gengar = new Gengar();
        configurarMovimientosGengar(gengar);
        return gengar;
    }

    private static void configurarMovimientosGengar(Gengar gengar) {
    }

    private static void configurarMovimientosCharizard(Charizard charizard) {
        Movimiento[] movimientos = {
                crearLanzallamas(),
                crearGiroFuego(),
                crearDiaSoleado(),
                crearGarraDragon()
        };

        for (int i = 0; i < movimientos.length; i++) {
            if (charizard.puedeAprender(movimientos[i])) {
                charizard.aprenderMovimiento(movimientos[i], i);
            }
        }
    }

    private static void configurarMovimientosBlastoise(Blastoise blastoise) {
        blastoise.aprenderMovimiento(FabricaMovimientos.crearHidrobomba(), 0);
        blastoise.aprenderMovimiento(FabricaMovimientos.crearAcuaCola(), 1);
        blastoise.aprenderMovimiento(FabricaMovimientos.crearRefugio(), 2);
        blastoise.aprenderMovimiento(FabricaMovimientos.crearCabezazo(), 3);
    }

    public static List<Pokemon> crearEquipoPredefinido() {
        List<Pokemon> equipo = new ArrayList<>();
        equipo.add(crearCharizard());
        equipo.add(crearBlastoise());
        equipo.add(crearVenusaur());
        equipo.add(crearGengar());
        return equipo;
    }
}
