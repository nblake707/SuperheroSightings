/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author blake
 */
public class OrganizationDaoImpl implements SuperheroSightingsOrganizationDao {

   private static final String SQL_INSERT_ORGANIZATION = "INSERT INTO Organizations(OrganizationName, isHeroOrganization, OrganizationDescription, OrganizationCountry, OrganizationState, OrganizationCity, OrganizationStreet, OrganizationZipCode) " 
        + "VALUES(?,?,?,?,?,?,?,?);"; 

    private static final String SQL_SELECT_ORGANIZATION
            = "SELECT * FROM Organizations WHERE OrganizationID = ?;";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "SELECT * FROM Organizations;";

    private static final String SQL_UPDATE_ORGANIZATION
            = "UPDATE Organizations set "
            + "OrganizationName = ?, isHeroOrganization = ?, OrganizationDescription = ?, OrganizationCountry = ?, "
            + "OrganizationState = ?, OrganizationCity = ?, OrganizationStreet = ?, OrganizationZipcode = ? "
            + "where OrganizationID = ?;";

    private static final String SQL_DELETE_ORGANIZATION
            = "DELETE FROM Organizations "
            + " where OrganizationID  = ?;";

    private static final String SQL_INSERT_ORGANIZATION_MEMBERS
             = "insert into OrganizationMembers (OrganizationID, PersonID) values(?, ?);";
           

    private static final String SQL_DELETE_ORGANIZATION_MEMBERS
            = "DELETE FROM OrganizationMembers "
            + "where OrganizationID = ?;";


    private static final String SQL_GET_ORGANIZATIONS_BY_PERSON_ID
            = "select o.OrganizationID, o.OrganizationName, o.isHeroOrganization, o.OrganizationDescription, "
            + "o.OrganizationCountry, o.OrganizationState, o.OrganizationCity, o.OrganizationStreet, o.OrganizationZipCode "
            + "from Organizations o join OrganiztionMember om on PersonID "
            + "Where o.OrganizationID = om.OrganizationID and om.PersonID = ?;";

    //Injecting Jdbdctemplate into code, to allow us to talk to sql database         
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization createOrganization(Organization organization) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
               organization.getIsItAHeroOrganization(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCountry(),
                organization.getOrganizationState(),
                organization.getOrganizationCity(),
                organization.getOrganizationStreet(),
                organization.getOrganizationZipcode()
                
        );

        int organizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(organizationId);

        insertOrganizationMembers(organization);

        return organization;
    }

    @Override
    public Organization getOrganizationById(int organizationId) throws SuperheroSightingsPersistenceException {
        try {

            Organization organization = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationId);

//           organization.setListOfPersons(findPersonsForOrganization(organization));  
            return organization;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public void updateOrganization(Organization organization) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getIsItAHeroOrganization(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCountry(),
                organization.getOrganizationState(),
                organization.getOrganizationCity(),
                organization.getOrganizationStreet(),
                organization.getOrganizationZipcode(),
                organization.getOrganizationId());

      
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS, organization.getOrganizationId());
      
    }

    @Override
    public void deleteOrganization(int organizationId) throws SuperheroSightingsPersistenceException {
        //Deletes person from person database
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);

        //Delete OrganizationMembers relationship from organization by removing org from bridge
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS, organizationId);
    }

    // Helper Methods 
    private void insertOrganizationMembers(Organization organization) {
        final int organizationId = organization.getOrganizationId();
        final List<Person> persons = organization.getListOfPersons();

        // Update the OrganizationMembers bridge add each person of this organization
        for (Person currentPerson : persons) {
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_MEMBERS,
                    organizationId,
                    currentPerson.getPersonId());
        }
    }
//    //Gets back all the persons for a organization by joining Person and OrganizationMembers tables
//    private List<Person> findPersonsForOrganization(Organization organization) {
//            return jdbcTemplate.query(SQL_GET_ORGANIZATIONS_BY_PERSON_ID,
//                              new PersonMapper(), 
//                              organization.getOrganizationId());
//    }

//    private List<Organization> associatePersonsWithOrganizations(List<Organization> orgList) {
//        // set the complete list of Person ids for each Organization
//    for (Organization currentOrganization : orgList) {
//        // add the Person to current Organization
//        currentOrganization.setListOfPersons(findPersonsForOrganization(currentOrganization));   
//    }
//    return orgList;
//    }
    @Override
    public List<Organization> findOrganizationsForPerson(Person person) throws SuperheroSightingsPersistenceException {
        List<Organization> org = jdbcTemplate.query(SQL_GET_ORGANIZATIONS_BY_PERSON_ID, new OrganizationMapper(), person.getPersonId());

        
        return org;
    }

    @Override
    public List<Organization> findOrganizationsForUser(User user) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {

            Organization organization = new Organization();

            organization.setOrganizationId(rs.getInt("OrganizationID"));
            organization.setOrganizationName(rs.getString("OrganizationName"));
            organization.setIsItAHeroOrganization(rs.getBoolean("isHeroOrganization"));
            organization.setOrganizationDescription(rs.getString("OrganizationDescription"));
            organization.setOrganizationCountry(rs.getString("OrganizationCountry"));
            organization.setOrganizationState(rs.getString("OrganizationState"));
            organization.setOrganizationCity(rs.getString("OrganizationCity"));
            organization.setOrganizationStreet(rs.getString("OrganizationStreet"));
            organization.setOrganizationZipcode(rs.getString("OrganizationZipCode"));
           ;

            return organization;
        }

    }

  

}
