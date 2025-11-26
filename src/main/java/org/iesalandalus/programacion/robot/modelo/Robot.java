package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public class Robot {
    private Zona zona;
    private Orientacion orientacion;
    private Coordenada coordenada;

    public Robot(){
        zona = new Zona();
        coordenada = zona.getCentro();
        orientacion = Orientacion.NORTE;
    }

    public Robot(Zona zona){
        setZona(zona);
        orientacion = Orientacion.NORTE;
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona, Orientacion orientacion){
        setZona(zona);
        setOrientacion(orientacion);
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona, Orientacion orientacion, Coordenada coordenada){
        setZona(zona);
        setOrientacion(orientacion);
        setCoordenada(coordenada);

    }

    public Robot(Robot robot){
        Objects.requireNonNull(robot, "El robot no puede ser nulo.");
        zona = robot.zona;
        orientacion = robot.orientacion;
        coordenada = robot.coordenada;
    }


    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        Objects.requireNonNull(zona, "La zona no puede ser nula.");
        this.zona = zona;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Orientacion orientacion) {
        Objects.requireNonNull(orientacion, "La orientación no puede ser nula.");
        this.orientacion = orientacion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        Objects.requireNonNull(coordenada, "La coordenada no puede ser nula.");
        if (!zona.pertenece(coordenada)){
            throw new IllegalArgumentException("La coordenada no pertenece a la zona.");
        }
        this.coordenada = coordenada;
    }

    public void avanzar() throws RobotExcepcion {
        try {
            switch (orientacion){
                case SUR -> setCoordenada(new Coordenada(coordenada.x(), coordenada.y() - 1));
                case ESTE -> setCoordenada(new Coordenada(coordenada.x() + 1, coordenada.y()));
                case NORTE -> setCoordenada(new Coordenada(coordenada.x(), coordenada.y() + 1));
                case OESTE -> setCoordenada(new Coordenada(coordenada.x() - 1, coordenada.y()));
                case NORESTE ->setCoordenada(new Coordenada(coordenada.x() + 1, coordenada.y() + 1));
                case SURESTE -> setCoordenada(new Coordenada(coordenada.x() + 1, coordenada.y() - 1));
                case NOROESTE -> setCoordenada(new Coordenada(coordenada.x() - 1, coordenada.y() + 1));
                case SUROESTE -> setCoordenada(new Coordenada(coordenada.x() - 1, coordenada.y() - 1));
            }
        } catch (IllegalArgumentException e){
            throw new RobotExcepcion("No se puede avanzar, ya que se sale de la zona.");
        }
    }

    public void girarALaDerecha(){
        switch (orientacion){
            case SUR -> orientacion = Orientacion.SUROESTE;
            case SUROESTE -> orientacion = Orientacion.OESTE;
            case OESTE -> orientacion = Orientacion.NOROESTE;
            case NOROESTE -> orientacion = Orientacion.NORTE;
            case NORTE -> orientacion = Orientacion.NORESTE;
            case NORESTE -> orientacion = Orientacion.ESTE;
            case ESTE -> orientacion = Orientacion.SURESTE;
            case SURESTE -> orientacion = Orientacion.SUR;
        }
    }

    public void girarALaIzquierda(){
        switch (orientacion){
            case SUR -> orientacion = Orientacion.SURESTE;
            case SURESTE -> orientacion = Orientacion.ESTE;
            case ESTE -> orientacion = Orientacion.NORESTE;
            case NORESTE -> orientacion = Orientacion.NORTE;
            case NORTE -> orientacion = Orientacion.NOROESTE;
            case NOROESTE -> orientacion = Orientacion.OESTE;
            case OESTE -> orientacion = Orientacion.SUROESTE;
            case SUROESTE -> orientacion = Orientacion.SUR;
        }
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(zona, robot.zona) && orientacion == robot.orientacion && Objects.equals(coordenada, robot.coordenada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zona, orientacion, coordenada);
    }

    @Override
    public String toString() {
        return "Robot{" +
                "zona=" + zona +
                ", orientacion=" + orientacion +
                ", coordenada=" + coordenada +
                '}';
    }
}
