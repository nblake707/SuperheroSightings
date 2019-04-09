/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;

/**
 *
 * @author blake
 */
public class SuperpowerServiceLayerImpl implements SuperheroSightingsSuperpowerServiceLayer{
    
       SuperheroSightingsSuperpowerDao superpowerDao;
 @Inject
   public SuperpowerServiceLayerImpl(SuperheroSightingsSuperpowerDao superpowerDao) {
       this.superpowerDao = superpowerDao;
   }
   

    @Override
    public Superpower createSuperpower(Superpower superpower) {
        Superpower sp = new Superpower();
           try {
               sp = superpowerDao.createSuperpower(superpower);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return sp;
                  
    }

    @Override
    public Superpower getSuperpowerById(int superpowerId) throws EntityNotFoundException {
        Superpower sp = new Superpower();
        
           try {
               sp = superpowerDao.getSuperpowerById(superpowerId);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return sp;
    }
        

    @Override
    public List<Superpower> getAllSuperpowers() {
      try {
               return superpowerDao.getAllSuperpowers();
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null;
          
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
           try {
               superpowerDao.updateSuperpower(superpower);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @Override
    public void deleteSuperpower(int superpowerId) {
           try {
               superpowerDao.deleteSuperpower(superpowerId);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @Override
    public List<Superpower> findSuperpowersForPerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
