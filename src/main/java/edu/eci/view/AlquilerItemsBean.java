package edu.eci.cvds.view;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.*;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import java.util.List;
import java.util.ArrayList;

import java.sql.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "AlquilerBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean {

    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente cliente;
    private long costoAlquiler;

    /**
     * Retorna una lista con la informacion de los items rentados por un cliente dado
     * @param documento documento del cliente solicitado
     * @return lista con la informacion de los items rentados por un cliente dado
     */
    public List<ItemRentado> consultarItemsCliente(Cliente cliente) throws ExcepcionServiciosAlquiler{
        return serviciosAlquiler.consultarItemsCliente(cliente.getDocumento());
    }

    /**
    * Registra un nuevo alquiler con el item y numero de dias especificados
    * @param idItem identificador unico del item deseado
    * @param numDays numero de dias por los cuales se solicita el item
    */
    public void registrarAlquiler(int idItem , int numDays) throws ExcepcionServiciosAlquiler {
        Item item = serviciosAlquiler.consultarItem(idItem);
        serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()),cliente.getDocumento(),item,numDays);
    }

    public void setCostoAlquiler(long costoAlquiler){
	this.costoAlquiler = costoAlquiler;
    }
    
    public long getCostoAlquiler(){
	return costoAlquiler;
    }

    /**
    * Consulta los costos de rentar un item con base en el numero de dias dado
    * @param idItem id del item a rentar
    * @param nDays numero de dias a rentar
    */
    public void consultarCosto(String iditem , String nDays) throws ExcepcionServiciosAlquiler {
    	costoAlquiler = serviciosAlquiler.consultarCostoAlquiler(Integer.parseInt(iditem), Integer.parseInt(nDays));
    }

    /**
    * Consulta los costos de rentar un item con base en el numero de dias dado
    * @param idItem id del item a rentar
    * @param nDays numero de dias a rentar
    */
    public void consultarCosto(int iditem , int nDays) throws ExcepcionServiciosAlquiler {
    	costoAlquiler = serviciosAlquiler.consultarCostoAlquiler(iditem, nDays);
    }

    /**
    * Consulta la multa asociada a un item dado
    * @param idItem id del item a consultar
    */
    public long consultarMulta(int iditem) throws ExcepcionServiciosAlquiler {
	return serviciosAlquiler.consultarMultaAlquiler(iditem, new Date(System.currentTimeMillis()));
    }
}