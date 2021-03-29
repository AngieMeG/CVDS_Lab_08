package edu.eci.cvds.view;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import java.util.List;
import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "AlquilerBean")
public class AlquilerItemsBean extends BasePageBean {

    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente cliente;
    private List<Cliente> added;

    /**
     * Retorna una lista con la informacion de los clientes almacenados en la base de datos
     * @return todos los clientes de la base de datos
     * @throws ExcepcionServiciosAlquiler
     */
    public AlquilerItemsBean(){
	added = new ArrayList<Cliente>();
    }



    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler{
        try {
	    List<Cliente> clientes = new ArrayList<Cliente>();
	    clientes.addAll(added);
	    clientes.addAll(serviciosAlquiler.consultarClientes());
            return clientes;   
        } catch (Exception e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar la tabla Clientes");
        }
    }

    /**
      * Registra un cliente nuevo en la base de datos
      * @param Nombre nombre del nuevo cliente
      * @param Documeto documento de identidad del nuevo cliente
      * @param Telefono telefono del nuevo cliente
      * @param Direccion direccion del nuevo cliente
      * @param Email email del nuevo cliente
      * 
      */
    public void registrar(String nombre, long documento, String telefono, String direccion, String email) throws ExcepcionServiciosAlquiler{
        try {
            added.add(new Cliente(nombre,documento,telefono,direccion,email));
	    	
            serviciosAlquiler.registrarCliente(added.get(added.size() - 1));
        } catch (Exception e) {
            throw new ExcepcionServiciosAlquiler(e.toString());
        }
    }

    public void setCliente(Cliente cliente){
	this.cliente = cliente;
    }
    
    public Cliente getCliente(){
	return this.cliente;
    }

}