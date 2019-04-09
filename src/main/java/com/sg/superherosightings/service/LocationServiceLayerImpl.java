/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsLocationServiceLayer;

/**
 *
 * @author blake
 */
public class LocationServiceLayerImpl implements SuperheroSightingsLocationServiceLayer{
  
    ///
   SuperheroSightingsLocationDao locationDao;
   SuperheroSightingsSightingDao sightingDao;
 @Inject
    public LocationServiceLayerImpl(SuperheroSightingsLocationDao locationDao, SuperheroSightingsSightingDao sightingDao ) {
        this.locationDao = locationDao;
        this.sightingDao = sightingDao;
    }
 
 

    @Override
    public Location createLocation(Location lctn) {
        Location loc = new Location();
        
       try {
           loc = locationDao.createLocation(lctn);
       } catch (SuperheroSightingsPersistenceException ex) {
           Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return loc;
    }

    @Override
    public Location getLocationById(int locationId) throws EntityNotFoundException {
       Location loc = new Location();
       
       try {
           loc = locationDao.getLocationById(locationId);
       } catch (SuperheroSightingsPersistenceException ex) {
           Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return loc;
    }

    @Override
    public List<Location> getAllLocations() {
       try {
           return locationDao.getAllLocations();
       } catch (SuperheroSightingsPersistenceException ex) {
           Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
    }

    @Override
    public void updateLocation(Location lctn) {
       try {
           locationDao.updateLocation(lctn);
       } catch (SuperheroSightingsPersistenceException ex) {
           Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    //bring in 

    @Override
    public void deleteLocation(int locationId) {
////        List<Sighting> sightings = new ArrayList();
//        sightings = sightingDao.getAllSightingsByLocation(locationId);
////        //loop 
////        for()
       try {
           locationDao.deleteLocation(locationId);
          
       } catch (SuperheroSightingsPersistenceException ex) {
           Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
    }


    @Override
    public List<Location> findAllLocationsPersonWasSightedAt(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
