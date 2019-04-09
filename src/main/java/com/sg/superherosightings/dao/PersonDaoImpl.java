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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

public class PersonDaoImpl implements SuperheroSightingsPersonDao {

   

    private static final String SQL_INSERT_PERSON
            = "INSERT INTO Person"
            + "(isHero, FirstName,LastName, PersonDescription)"
            + "values (?,?,?,?);";

    private static final String SQL_SELECT_PERSON
            = "SELECT * FROM Person where PersonID = ?;";

    private static final String SQL_SELECT_ALL_PERSONS
            = "SELECT * FROM Person;";

    private static final String SQL_UPDATE_PERSON
            = " UPDATE Person set FirstName = ?, LastName = ?, isHero = ?, PersonDescription = ? where PersonID = ?;";

    private static final String SQL_DELETE_PERSON
            = " DELETE from Person where PersonID = ?;";

    private static final String SQL_INSERT_PERSON_SUPERPOWERS
            = "INSERT INTO PersonSuperpowers"
            + "(PersonID,SuperpowerID)"
            + "values (?,?);";

    private static final String SQL_DELETE_PERSON_SUPERPOWERS
            = "DELETE FROM PersonSuperpowers where PersonID = ?;";

    //Need to access a list of persons that belong to Org based on org ID, need Person Dao to retrieve this
    private static final String SQL_SELECT_PERSONS_BY_ORGANIZATION_ID
            = "SELECT per.isHero, per.FirstName,per.LastName, per.PersonDescription"
            + "FROM Person per "
            + "JOIN OrganizationMembers orgmem ON per.PersonID =  orgmem.PersonID "
            + "where orgmem.OrganizatioID = ?;";

    private static final String SQL_INSERT_ORGANIZATION_MEMBERS
            = "INSERT INTO OrganizationMembers"
            + "(OrganizationID, PersonID)"
            + "values (?,?);";

    private static final String SQL_DELETE_ORGANIZATION_MEMBERS
            = "DELETE FROM OrganizationMembers "
            + "where PersonID = ?;";

    //Need to get all persons by sightingID
    private static final String SELECT_PERSON_BY_SIGHTING_ID
            = "SELECT per.isHero, per.FirstName,per.LastName, per.PersonDescription "
            + "FROM Person per "
            + "JOIN Sightings sig ON per.PersonID = sig.PersonID "
            + "where SightingID = ?;";
    
    //SELECT WHERE PERSON AND SIGHINGS PERSONID MATCH BY LOCATION
    private static final String SQL_SELECT_PERSON_BY_LOCATION_ID
           = "SELECT p.* FROM Person p JOIN Sightings s ON s.PersonID = s.PersonID WHERE LocationID = ?;";

    private static final String SQL_SELECT_PERSONS_BY_SUPERPOWER
            = "SELECT per.* "
            + "FROM Person per "
            + "JOIN PersonSuperpowers persp ON per.PersonID = persp.PersonID "
            + "where persp.SuperpowerID = ?;";

private static final String SQL_SELECT_PERSON_BY_SIGHTING_ID
            = "Select per.PersonID, per.FirstName, per.LastName, per.isHero, per.PersonDescription "
            + "from Person per join Sightings on per.PersonID = Sightings.SightingID where "
            + "SightingID = ? ";
    
    
    
    //Injecting Jdbdctemplate into code, to allow us to talk to sql database
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person createPerson(Person person) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_INSERT_PERSON,
                person.getIsHero(),
                person.getFirstName(),
                person.getLastName(),
                person.getDescriptionOfPerson());
        //Takes last ID created in database  & auto increments 
        int personId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        person.setPersonId(personId);
        //PersonSuperpower needs to pass in person using personID for each superpower person has?? maybe servicelayer
        return person;
    }

    @Override
    public Person getPersonById(int personId) throws SuperheroSightingsPersistenceException {
        try {

            Person person = jdbcTemplate.queryForObject(SQL_SELECT_PERSON, new PersonMapper(), personId);

            return person;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Person> getAllPersons() throws SuperheroSightingsPersistenceException {
        //Get list of all persons 
        return jdbcTemplate.query(SQL_SELECT_ALL_PERSONS, new PersonMapper());

    }
    
    //had to change this the person. portion was not in order and being passed into the right spaces
    //the person.getpersonId portion was outside of the parenthesis and not included in the statement
    //probably explains why there was no value for parameter 5 of my prepared statement

    @Override
    public void updatePerson(Person person) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_UPDATE_PERSON,
                
                person.getFirstName(),
                person.getLastName(),
                person.getIsHero(),
                person.getDescriptionOfPerson(),
                person.getPersonId());
        //Get PersonSuperpowers to delete person from bridge table relationship
        jdbcTemplate.update(SQL_DELETE_PERSON_SUPERPOWERS,
                person.getPersonId());
        //Get OrganizationMembers to delete person from bridge table 
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS,
                person.getPersonId());

    }

    @Override
    public void deletePerson(int personId) throws SuperheroSightingsPersistenceException {
       
        jdbcTemplate.update(SQL_DELETE_PERSON, personId);

      
        jdbcTemplate.update(SQL_DELETE_PERSON_SUPERPOWERS, personId);
    }

    
  
  
    
       @Override
    public List<Person> getAllPersonsSightedAtLocation(int locationID) throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_LOCATION_ID, new PersonMapper());    
    }
 
    @Override
    public Person findPersonForSighting(Sighting sighting) throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.queryForObject(SELECT_PERSON_BY_SIGHTING_ID, new PersonMapper(), sighting.getSightingId());
    }
    
    public Person getPersonBySightingId (int SightingId) throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_PERSON_BY_SIGHTING_ID, new PersonMapper(), SightingId);
    }

    @Override
    public List<Person> findPersonsForOrganization(Organization organization) throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_PERSONS_BY_ORGANIZATION_ID,
                new PersonMapper(),
                organization.getOrganizationId());
    }

    private void insertPersonSuperpowers(Person person) {
        final int personId = person.getPersonId();
        final List<Superpower> superpowers = person.getListOfSuperpowers();

        // Update the PersonSuperpowers bridge table with an entry for 
        // each superpower for this person
        for (Superpower currentSuperpower : superpowers) {
            jdbcTemplate.update(SQL_INSERT_PERSON_SUPERPOWERS,
                    personId,
                    currentSuperpower.getSuperpowerId());
        }
    }

    @Override
    public List<Person> findPersonsForSuperpower(Superpower superpower) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getAllPersonsSightedAtLocation(List<Sighting> sightingList) throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_LOCATION_ID, new PersonMapper()); 
    }

    // MAPPERS
    //Access Person table in database
    private static final class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {

            Person per = new Person();

            per.setPersonId(rs.getInt("PersonID"));
            per.setFirstName(rs.getString("FirstName"));
            per.setLastName(rs.getString("LastName"));
            per.setIsHero(rs.getBoolean("isHero"));
            per.setDescriptionOfPerson(rs.getString("PersonDescription"));

            return per;
        }
    }

    //Person needs to access the Superpower mapper in order to create JOIN
    private static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            //Name in quotes should appear extact to sql database    
            Superpower suppow = new Superpower();
            suppow.setSuperpowerId(rs.getInt("SuperpowerID"));
            suppow.setSuperpowerName(rs.getString("SuperpowerName"));
            suppow.setSuperpowerDescription(rs.getString("SuperpowerDescription"));

            return suppow;
        }

    }

}
