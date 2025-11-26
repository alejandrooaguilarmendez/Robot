package org.iesalandalus.programacion.robot.vista;

import org.iesalandalus.programacion.robot.modelo.*;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
    private Consola(){}

    public static void mostrarMenuPrincipal(){
        System.out.println("Bienvenido al menú principal");
        System.out.println("Opción 1: Controlar un robot por defecto");
        System.out.println("Opción 2: Controlar un robot eligiendo su zona");
        System.out.println("Opción 3: Controlar un robot eligiendo su zona y su orientación");
        System.out.println("Opción 4: Controlar un robot eligiendo su zona, orientación y coordenada inicial");
        System.out.println("Opción 5: Ejecutar comando");
        System.out.println("Opción 6: Salir");
    }

    public static int elegirOpcion(){
        int opcion;
        do {
            System.out.print("Elige una opción del menú: ");
            opcion = Entrada.entero();
        } while (opcion < 1 || opcion > 6);
        return opcion;
    }

    public static Zona elegirZona(){
        int ancho;
        int alto;
        Zona zona = null;
        do {
            System.out.print("Dime el ancho de la zona: ");
            ancho = Entrada.entero();
            System.out.print("Dime el alto de la zona: ");
            alto = Entrada.entero();
            try {
                zona = new Zona(ancho, alto);
            } catch (IllegalArgumentException iae){
                System.out.println("Zona no válida, Inténtalo de nuevo");
            }
        } while (zona == null);
        return zona;
    }

    public static void mostrarMenuOrientacion(){
        System.out.println("ORIENTACIONES:");
        System.out.println("1. Norte");
        System.out.println("2. Noreste");
        System.out.println("3. Este");
        System.out.println("4. Sureste");
        System.out.println("5. Sur");
        System.out.println("6. Suroeste");
        System.out.println("7. Oeste");
        System.out.println("8. Noroeste");
    }

    public static Orientacion elegirOrientacion(){
        Orientacion orientacion = null;
        int opcion;

        do {
            System.out.print("Elige una orientación");
            opcion = Entrada.entero();
        } while (opcion < 1 || opcion > 8);

        switch (opcion){
            case 1 -> orientacion = Orientacion.NORTE;
            case 2 -> orientacion = Orientacion.NORESTE;
            case 3 -> orientacion = Orientacion.ESTE;
            case 4 -> orientacion = Orientacion.SURESTE;
            case 5 -> orientacion = Orientacion.SUR;
            case 6 -> orientacion = Orientacion.SUROESTE;
            case 7 -> orientacion = Orientacion.OESTE;
            case 8 -> orientacion = Orientacion.NOROESTE;
        }
        return orientacion;
    }

    public static Coordenada elegirCoordenada(){
        Coordenada coordenada;
        int x;
        int y;
        System.out.print("Dime la X de la coordenada: ");
        x = Entrada.entero();
        System.out.print("Dime la Y de la coordenada: ");
        y = Entrada.entero();
        coordenada = new Coordenada(x, y);
        return coordenada;
    }

    public static char elegirComando(){
        char comando;
        System.out.print("Dime que comando quieres usar: ");
        comando = Entrada.caracter();
        return comando;
    }

    public static void mostrarRobot(ControladorRobot controladorRobot){
        if (controladorRobot.getRobot() == null){
            System.out.println("El robot es nulo");
        } else {
            System.out.println(controladorRobot.getRobot());
        }
    }

    public static void despedirse(){
        System.out.println("Adios, gracias por jugar");
    }
}
