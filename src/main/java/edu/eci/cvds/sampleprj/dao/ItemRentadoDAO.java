package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {

   public List<ItemRentado> load(long idClient) throws PersistenceException;

}
