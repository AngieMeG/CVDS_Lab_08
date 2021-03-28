package edu.eci.cvds.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        for(int i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
                serviciosAlquiler.consultarCliente(i);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }

            // Validate no Client was found;
            Assert.assertTrue(r);
        }
    }

    @Test
    public void deberiaInsertarUnCliente(){
        try{
            ArrayList<ItemRentado> rentados = new ArrayList<ItemRentado>();
            serviciosAlquiler.registrarCliente(new Cliente("Angie", 123, "456", "789", "101", false, rentados));
            assertTrue(serviciosAlquiler.consultarClientes().size() > 0);
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void deberiaSerClienteValido(){
        try{
            ArrayList<ItemRentado> rentados = new ArrayList<ItemRentado>();
            serviciosAlquiler.registrarCliente(new Cliente("Angie", 123, "456", "789", "101", false, rentados));
            Cliente cliente = serviciosAlquiler.consultarCliente(123L);
            assertEquals(123L, cliente.getDocumento());
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void deberiaSerClienteNoValido(){
        try{
            Cliente cliente = serviciosAlquiler.consultarCliente(-10999900099L);
            fail("No lanzo excepcion.");
        } catch(ExcepcionServiciosAlquiler e){
            assertEquals(ExcepcionServiciosAlquiler.NO_CLIENT_REGISTRED, e.getMessage());
        }
    }

    @Test
    public void deberiaInsertarUnTipo(){
        try{
            //serviciosAlquiler.registrarTipoItem(new TipoItem(1, "Simulacion"));
            assertTrue(serviciosAlquiler.consultarTiposItem().size() > 0);
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void deberiaConsultarTipoItemValido(){
        try{
            //serviciosAlquiler.registrarTipoItem(new TipoItem(2, "Accion"));
            assertEquals(2, serviciosAlquiler.consultarTipoItem(2).getID());
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void noDeberiaConsultarTipoItem(){
        try{
            serviciosAlquiler.consultarTipoItem(4);
            fail("No lanzo excepcion.");
        } catch(ExcepcionServiciosAlquiler e){
            assertEquals(ExcepcionServiciosAlquiler.NO_ITEM_TYPE, e.getMessage());
        }
    }

    @Test
    public void deberiaInsertarunItem(){
        try{
            TipoItem ti = serviciosAlquiler.consultarTipoItem(2);
            Item i = new Item(ti, 1, "a", "b", Date.valueOf("2020-03-28"), 10000L, "c", "d");
            serviciosAlquiler.registrarItem(i);
            assertTrue(serviciosAlquiler.consultarItemsDisponibles().size() > 0);
        } catch(ExcepcionServiciosAlquiler e){    
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void deberiaConsultarItemValido(){
        try{
            TipoItem ti = serviciosAlquiler.consultarTipoItem(2);
            Item i = new Item(ti, 1, "a", "b", Date.valueOf("2020-03-28"), 10000L, "c", "d");
            serviciosAlquiler.registrarItem(i);
            assertEquals(1, serviciosAlquiler.consultarItem(1).getId());
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    
    @Test
    public void noDeberiaConsultarItem(){
        try{
            serviciosAlquiler.consultarItem(20);
            fail("No lanzo excepcion.");
        } catch(ExcepcionServiciosAlquiler e){
            assertEquals(ExcepcionServiciosAlquiler.NO_ITEM, e.getMessage());
        }
    }

    @Test
    public void deberiaInsertarunAlquiler(){
        try{
            TipoItem ti = serviciosAlquiler.consultarTipoItem(2);
            Item i = new Item(ti, 1, "a", "b", Date.valueOf("2020-03-28"), 10000L, "c", "d");
            ArrayList<ItemRentado> rentados = new ArrayList<ItemRentado>();
            Cliente c = new Cliente("Angie", 123, "456", "789", "101", false, rentados);
            serviciosAlquiler.registrarAlquilerCliente(Date.valueOf("2020-03-28"), c.getDocumento(), i, 10);
            assertTrue(serviciosAlquiler.consultarItemsCliente(c.getDocumento()).size() > 0);
        } catch(ExcepcionServiciosAlquiler e){    
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void deberiaConsultarAlquiler(){
        try{
            TipoItem ti = serviciosAlquiler.consultarTipoItem(2);
            Item i = new Item(ti, 1, "a", "b", Date.valueOf("2020-03-28"), 10000L, "c", "d");
            ArrayList<ItemRentado> rentados = new ArrayList<ItemRentado>();
            Cliente c = new Cliente("Angie", 123, "456", "789", "101", false, rentados);
            //serviciosAlquiler.registrarAlquilerCliente(Date.valueOf("2020-03-28"), c.getDocumento(), i, 10);
            boolean found = false;
            for (ItemRentado item : serviciosAlquiler.consultarItemsCliente(c.getDocumento())){
                if (item.getItem().getId() == 1) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }
    
    @Test
    public void noDeberiaTenerAlquiler(){
        ArrayList<ItemRentado> rentados = new ArrayList<ItemRentado>();
        Cliente c = new Cliente("Angie", 12, "456", "789", "101", false, rentados);
        try{
            serviciosAlquiler.registrarCliente(c);
            assertTrue(serviciosAlquiler.consultarItemsCliente(c.getDocumento()).size() == 0);
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void deberiaConsultarMultaAlquiler(){
        try{
            Date fecha = Date.valueOf("2020-04-28");
            assertTrue(serviciosAlquiler.consultarMultaAlquiler(1, fecha) > 0);
        } catch(ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void noDeberiaConsultarMultaAlquiler(){
        try{
            Date fecha = Date.valueOf("2020-04-28");
            TipoItem ti = serviciosAlquiler.consultarTipoItem(2);
            Item i = new Item(ti, 2, "a1", "b1", Date.valueOf("2021-03-28"), 10003L, "c1", "d1");
            //serviciosAlquiler.registrarItem(i);
            serviciosAlquiler.consultarMultaAlquiler(2, fecha);
            fail("No lanzo excepcion.");
        } catch(ExcepcionServiciosAlquiler e){
            assertEquals(ExcepcionServiciosAlquiler.NO_ITEM_RENTED, e.getMessage());
        }
    }

    @Test
    public void deberiaConsultarCostoAlquiler(){
        try{
            int dias = 10;
            long costoEsperado = serviciosAlquiler.consultarItem(1).getTarifaxDia() * dias;
            long costo = serviciosAlquiler.consultarCostoAlquiler(1, dias);
            assertEquals(costoEsperado, costo);
        } catch (ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void noDeberiaConsultarCostoAlquiler(){
        try{
            int dias = -10;
            serviciosAlquiler.consultarCostoAlquiler(1, dias);
            fail("No lanzo excepcion.");
        } catch (ExcepcionServiciosAlquiler e){
            assertEquals(ExcepcionServiciosAlquiler.INVALID_DAYS, e.getMessage());
        }
    }

    @Test
    public void deberiaActualizarTarifa(){
        try{
            serviciosAlquiler.actualizarTarifaItem(1, 20000L);
            assertEquals(20000L, serviciosAlquiler.consultarItem(1).getTarifaxDia());
        } catch (ExcepcionServiciosAlquiler e){
            fail("Lanzo excepcion.");
        }
    }

    @Test
    public void noDeberiaActualizarTarifa(){
        try{
            serviciosAlquiler.actualizarTarifaItem(1, -20000L);
            fail("No lanzo excepcion.");
        } catch (ExcepcionServiciosAlquiler e){
            assertEquals(ExcepcionServiciosAlquiler.INVALID_RATE, e.getMessage());
        }
    }

    @Test
    public void deberiaVetarCliente(){
        try{
            serviciosAlquiler.vetarCliente(123, true);
            assertTrue(serviciosAlquiler.consultarCliente(123).isVetado());
        } catch(ExcepcionServiciosAlquiler e){
            try{
                serviciosAlquiler.vetarCliente(123, false);
            } catch(ExcepcionServiciosAlquiler ex){}
            fail("Lanzo excepcion.");
        }
    }
}