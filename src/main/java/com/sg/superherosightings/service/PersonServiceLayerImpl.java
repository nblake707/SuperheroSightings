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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;

/**
 *
 * @author blake
 */
public class PersonServiceLayerImpl implements SuperheroSightingsPersonServiceLayer {
    
    SuperheroSightingsPersonDao personDao;
    SuperheroSightingsOrganizationDao orgDao;
    SuperheroSightingsSuperpowerDao superpowerDao;
    
    List<Person> PersonsFromSightingsByLocation = new ArrayList<>();
    
 @Inject
    public PersonServiceLayerImpl(SuperheroSightingsPersonDao personDao) {
        this.personDao = personDao;
    }
    

    @Override
    public Person createPerson(Person person) {
        Person per = new Person();
        
        try {
            per = personDao.createPerson(person);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return per;
        
    }

    @Override
    public Person getPersonById(int personId) throws EntityNotFoundException {
//        Person per = new Person();
        
        try {
          return personDao.getPersonById(personId);
            
//            per.setListOfOrganizations(orgDao.findOrganizationsForPerson(per));
//            per.setListOfSuperpowers(superpowerDao.findSuperpowersForPerson(per));
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        

    @Override
    public List<Person> getAllPersons() {
        try {
            return personDao.getAllPersons();
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        

    @Override
    public void updatePerson(Person person) {
        try {
            personDao.updatePerson(person);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletePerson(int personId) {
        try {
            personDao.deletePerson(personId);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Person> getAllPersonsSightedAtLocation(List<Sighting> sightingList) {
        try {
            return personDao.getAllPersonsSightedAtLocation(sightingList);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void setPersonsFromSightingsByLocation(List<Person> personList) {
        this.setPersonsFromSightingsByLocation(personList); 
    }

    @Override
    public List<Person> getPersonsFromSightingsByLocation() {
      return PersonsFromSightingsByLocation;
    }

    @Override
    public Person findPersonForSighting(Sighting sighting)  {
        try{
            Person per = personDao.findPersonForSighting(sighting);
            
            per.setListOfSuperpowers(superpowerDao.findSuperpowersForPerson(per));
            return per;
            
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void setGlobalPersonList(List<Person> personList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getGlobalPersonList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findPersonsForOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findPersonsForSuperpower(Superpower superpower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getAllPersonsSightedAtLocation(int locationID) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
