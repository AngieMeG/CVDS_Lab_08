package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") long id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id, El id del cliente al que se le registrara un item como rentado
     * @param idit, El id del item que se va a rentar
     * @param fechainicio, la fecha de inicio de la renta
     * @param fechafin, la fecha en la que se termina la renta
     */
    public void agregarItemRentadoACliente(@Param("idcli") int id, 
            @Param("iditr") int idit, 
            @Param("fini")Date fechainicio,
            @Param("ffin")Date fechafin);

    /**
     * Consultar todos los clientes
     * @return lista de todos los clientes
     */
    public List<Cliente> consultarClientes();
    
    /**
     * Insertar un cliente
     * @param cl, Objeto cliente a insertar
     */
    public void insertarCliente(@Param("cli") Cliente cl);
}
