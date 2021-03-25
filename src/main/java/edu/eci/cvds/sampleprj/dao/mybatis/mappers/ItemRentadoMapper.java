package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 * Clase Mapper de la entidad item rentado.
 * @author Angie Medina
 * @author Jose Perez
 * @version 24/03/21 1.0 
 */
public interface ItemRentadoMapper {
    
    
    /**
     * Consulta todos los items rentados
     * @return lista de los items rentados
     */
    public List<ItemRentado> consultarItemsRentados();        
    
    /**
     * Consulta los items que ha rentado un cliente
     * @param id, Identificador del cliente
     * @return lista de los items rentados por un cliente
     */
    public List<ItemRentado> consultarItemsRentadosCliente(@Param("idIt") long id);
    
}