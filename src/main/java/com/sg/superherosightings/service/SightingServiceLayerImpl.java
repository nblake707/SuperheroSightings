/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSightingServiceLayer;

/**
 *
 * @author blake
 */
public class SightingServiceLayerImpl implements SuperheroSightingsSightingServiceLayer {
    
       SuperheroSightingsSightingDao sightingDao;
       SuperheroSightingsPersonDao personDao; 
       SuperheroSightingsLocationDao locationDao; 
       
 @Inject
    public SightingServiceLayerImpl(SuperheroSightingsSightingDao sightingDao, SuperheroSightingsPersonDao personDao, SuperheroSightingsLocationDao locationDao) {
        this.sightingDao = sightingDao;
        this.personDao = personDao;
        this.locationDao = locationDao;
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
       Sighting sight = new Sighting();
       
           try {
               sight = sightingDao.createSighting(sighting);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return sight;
    }



    @Override
    public Sighting getSightingById(int sightingId) throws EntityNotFoundException {
 
              try {

               return sightingDao.getSightingById(sightingId); 
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null;     

    }

    
    @Override
    public List<Sighting> getAllSightings() {
           try {
               return sightingDao.getAllSightings();
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null;
    }

    
    @Override
    public void updateSighting(Sighting sighting) {
           try {
               sightingDao.updateSighting(sighting);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    

    @Override
    public void deleteSighting(int sightingId) {
           try {
               sightingDao.deleteSighting(sightingId);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
    }


    @Override
    public List<Sighting> getLatestTenSightings() {
           try {
               return sightingDao.getLatestTenSightings();
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null;
    }

    @Override
    public List<Sighting> getAllSightingsByLocation(int locationId) {
           try {
               return sightingDao.getAllSightingsByLocation(locationId);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null;
    }


    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate justTheSightingDate) {
           try {
               return sightingDao.getAllSightingsByDate(justTheSightingDate);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null; 
    }
    
    
    @Override
    public List<Sighting> getAllSightingsByPerson(int PersonId) {
           try {
               return sightingDao.getAllSightingsByPerson(PersonId);
           } catch (SuperheroSightingsPersistenceException ex) {
               Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null;
    }
   
    //dont need these
    @Override
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightingsByLocalDate(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


       
    
}
