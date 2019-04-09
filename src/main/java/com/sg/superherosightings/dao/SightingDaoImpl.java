/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;

/**
 *
 * @author blake
 */
public class SightingDaoImpl implements SuperheroSightingsSightingDao {

    ///////////Prepared Statements///////////////////
    private static final String SQL_INSERT_SIGHTING
            = "INSERT INTO Sightings "
            + "(isHeroSighting,PersonID,LocationID,SightingDate) "
            + "values(?, ?, ?, ?); ";

    private static final String SQL_SELECT_SIGHTING
            = "SELECT * FROM Sightings where SightingID = ?;";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "SELECT * FROM Sightings ";

    private static final String SQL_UPDATE_SIGHTING
            = "UPDATE Sightings set "
            + "isHeroSighting = ?, PersonID = ?, LocationID = ? , SightingDate = ? "
            + "where SightingID = ?;";

    private static final String SQL_DELETE_SIGHTING
            = "DELETE FROM Sightings "
            + "where SightingID = ?; ";
    
    
   private static final String SQL_SELECT_SIGHTING_BY_DATE
            = "select * from Sightings where SightingDate = ?; ";
            
    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_PERSON_ID
            = "select * from Sightings where PersonID = ?; ";
            

    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION_ID
            = "select * From Sightings where LocationID = ?; ";


    private static final String SQL_SELECT_LATEST_TEN_SIGHTINGS
            = "SELECT * "
            + "FROM Sightings "
            + "ORDER BY SightingDate Desc LIMIT 10; ";
    
 
 

//    ///////////Helper Methods//////////
    
//    // These methods have been included in sightingsDAO to to extract a location and person objects
////  on each sighting object
//    // Extra steps are needed bc Eaching Sighting Object contain person and location objects
//    public Person findPersonForSighting(Sighting sighting) {
//        return jdbcTemplate.queryForObject(SQL_SELECT_PERSON_BY_SIGHTING_ID,
//                new PersonMapper(),
//                sighting.getSightingId());
//    }
//
//    public Location findLocationForSighting(Sighting sighting) {
//        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID,
//                new LocationMapper(),
//                sighting.getSightingId());
//    }

//    ////////////// TO: associate Person And Location With Sighting////////////////////////
//    public List<Sighting>
//            associatePersonAndLocationWithSighting(List<Sighting> sightingList) {
//        for (Sighting currentSighting : sightingList) {
//            //add the person to current sighting
//            currentSighting.setPerson(findPersonForSighting(currentSighting));
//            //add the location to current sightingList
//            currentSighting.setLocation(findLocationForSighting(currentSighting));
//        }
//        return sightingList;
//    }

    ////////////////////////End of Helper Methods//////////////////////////////////
    
    
    //////////////////////////////CRUD METHODS//////////////
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting createSighting(Sighting sighting) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getIsHeroSighting(),
                sighting.getPerson().getPersonId(),
                sighting.getLocation().getLocationId(),
                sighting.getJustTheSightingDate());
                   

        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(sightingId);
        return sighting;

    }
    

    @Override
    public Sighting getSightingById(int sightingId) throws SuperheroSightingsPersistenceException {
        try {
            // get the properties from the sighting table
           return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingId);
     
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    
    @Override
    public List<Sighting> getAllSightings() throws SuperheroSightingsPersistenceException {
        // get all the sightings
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
//        // set the Person and Location for each book
//        return associatePersonAndLocationWithSighting(sightingList);
    }


    @Override
    public void updateSighting(Sighting sighting) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getIsHeroSighting(),
                sighting.getPerson().getPersonId(),
                sighting.getLocation().getLocationId(),
                sighting.getJustTheSightingDate(),
                sighting.getSightingId());

    }

 
    @Override
    public void deleteSighting(int sightingId) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }
   


    
    @Override
    public List<Sighting> getLatestTenSightings() throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_LATEST_TEN_SIGHTINGS,
                new SightingMapper());
        
    }


    @Override
    public List<Sighting> getAllSightingsByLocation(int locationId) throws SuperheroSightingsPersistenceException {
       return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION_ID,
                new SightingMapper(),
                locationId);

    }


    @Override
    public List<Sighting> getAllSightingsByPerson(int PersonId) throws SuperheroSightingsPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_PERSON_ID, new SightingMapper(), PersonId); 
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate justTheSightingDate) throws SuperheroSightingsPersistenceException {
          return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_DATE, new SightingMapper(), justTheSightingDate);
    }

   
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sight = new Sighting();
           
            

            sight.setSightingId(rs.getInt("SightingID"));
            sight.setIsHeroSighting(rs.getBoolean("isHeroSighting"));
            sight.setPersonId(rs.getInt("PersonID"));
            sight.setLocationId(rs.getInt("LocationID"));
            sight.setSightingDate(rs.getTimestamp("SightingDate").toLocalDateTime());

            return sight;

        }

    }
       
    // not using this method 
    @Override
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //not using this method 
    @Override
    public List<Sighting> getAllSightingsByLocalDate(LocalDate ld) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  

}
