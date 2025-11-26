package org.iesalandalus.programacion.robot;

import org.iesalandalus.programacion.robot.modelo.*;
import org.iesalandalus.programacion.robot.vista.Consola;

import java.util.Objects;

public class Main {
    private static ControladorRobot controladorRobot;

    public static void main(String[] args) {
        int opcion;
        do {
            Consola.mostrarMenuPrincipal();
            opcion = Consola.elegirOpcion();
            try {
                ejecutarOpcion(opcion);
            } catch (NullPointerException e){
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 6);

    }

    private static void ejecutarOpcion(int opcion){
        switch (opcion){
            case 1 -> controladorRobotDefecto();
            case 2 -> controlarRobotZona();
            case 3 -> controlarRobotZonaOrientacion();
            case 4 -> controlarRobotZonaOrientacionCoordenada();
            case 5 -> ejecutarComando();
            case 6 -> Consola.despedirse();
        }
        if (opcion != 6) {
            Consola.mostrarRobot(controladorRobot);
        }

    }

    private static void controladorRobotDefecto(){
        controladorRobot = new ControladorRobot(new Robot());
    }

    private static void controlarRobotZona(){
        controladorRobot = new ControladorRobot(new Robot(Consola.elegirZona()));
    }

    private static void controlarRobotZonaOrientacion(){
        Consola.mostrarMenuOrientacion();
        controladorRobot = new ControladorRobot(new Robot(Consola.elegirZona(), Consola.elegirOrientacion()));
    }

    private static void controlarRobotZonaOrientacionCoordenada(){
        Consola.mostrarMenuOrientacion();
        try {
            controladorRobot = new ControladorRobot(new Robot(Consola.elegirZona(), Consola.elegirOrientacion(), Consola.elegirCoordenada()));
        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ejecutarComando(){
        try {
            controladorRobot.ejecutar(Consola.elegirComando());
        } catch (RobotExcepcion e){
            System.out.println("Comando no válido" + e.getMessage());
        }
    }
}
