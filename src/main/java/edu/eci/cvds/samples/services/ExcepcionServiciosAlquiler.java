package edu.eci.cvds.samples.services;

/**
 * Clase de excepciones para la clase ServiciosAlquiler
 * @author Angie Medina
 * @author Jose Perez
 * @version 24/03/21 1.0 
 */

public class ExcepcionServiciosAlquiler extends Exception{

    public static final String NO_CLIENT_REGISTRED = "Este cliente no se encuentra registrado.";
    public static final String NO_ITEM = "Este item no existe.";
    public static final String NO_ITEM_RENTED = "Este item no esta siendo rentado actualmente.";
    public static final String NO_ID_ITEM = "El Id no corresponde a un item.";
    public static final String ITEM_RENTED = "El item se encuentra rentado.";

    /**
     * Genera una excepcion con el mensaje dado
     * @param message mensaje de la excepción lanzada
     */
    public ExcepcionServiciosAlquiler(String message){
        super(message);
    }

    /**
     * Genera una excepcion con el mensaje dado y una excepcion
     * @param message mensaje de la excepción lanzada
     * @param e excepción
     */
    public ExcepcionServiciosAlquiler(String message, Exception e){
        super(message, e);
    }
}
