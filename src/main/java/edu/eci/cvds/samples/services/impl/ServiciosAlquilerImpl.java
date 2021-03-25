package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;

   @Inject
   private ClienteDAO clienteDAO;

   @Inject
   private TipoItemDAO tipoItemDAO;

   @Inject
   private ItemRentadoDAO itemRentadoDAO;

   public static final int MULTA_DIARIA = 5000;

   @Override
   public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler {
       try{
           return (int) itemDAO.load(itemId).getTarifaxDia() + MULTA_DIARIA;
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar valor multa.", ex);
       }
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
       try{
           Cliente cliente = clienteDAO.load(docu);
           if (cliente == null) throw new ExcepcionServiciosAlquiler(ExcepcionServiciosAlquiler.NO_CLIENT_REGISTRED);
           return cliente;
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar el cliente.", ex);
       }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try{
           Cliente cliente = clienteDAO.load(idcliente);
           if (cliente == null) throw new ExcepcionServiciosAlquiler()
           return itemRentadoDAO.load(idcliente);
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar los items del cliente.", ex);
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       try{
           return clienteDAO.loadAll();
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar los clientes.", ex);
       }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
       try{
           return itemDAO.loadAvailable();
       } catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar items disponibles", ex);
       }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       try{
           return tipoItemDAO.load(id);
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item", ex);
       }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       try{
           return tipoItemDAO.loadAll();
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar los tipos de items.", ex);
       }
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try{
           clienteDAO.save(c);
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al resgistrar el cliente.", ex);
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try{
           itemDAO.save(i);
       } catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al registrar el item.", ex);
       }
       throw new UnsupportedOperationException("Not supported yet."); 
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}