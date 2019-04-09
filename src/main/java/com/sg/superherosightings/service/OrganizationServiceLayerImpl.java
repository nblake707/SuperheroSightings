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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsOrganizationServiceLayer;

/**
 *
 * @author blake
 */
public class OrganizationServiceLayerImpl implements SuperheroSightingsOrganizationServiceLayer{
    
    SuperheroSightingsOrganizationDao organizationDao;
 @Inject
    public OrganizationServiceLayerImpl(SuperheroSightingsOrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
    

    @Override
    public Organization createOrganization(Organization organization) {
        Organization org = new Organization();
        
        try {
            org = organizationDao.createOrganization(organization);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return org;
    }

    @Override
    public Organization getOrganizationById(int organizationId) throws EntityNotFoundException {
                Organization org = new Organization();
        
        try {
            org = organizationDao.getOrganizationById(organizationId);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return org;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        try {
            return organizationDao.getAllOrganizations();
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void updateOrganization(Organization organization) {
        try {
            organizationDao.updateOrganization(organization);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteOrganization(int organizationId) {
        try {
            organizationDao.deleteOrganization(organizationId);
        } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Organization> findOrganizationsForPerson(Person person) {
        try {
            return organizationDao.findOrganizationsForPerson(person);
                    } catch (SuperheroSightingsPersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Organization> findOrganizationsForUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
