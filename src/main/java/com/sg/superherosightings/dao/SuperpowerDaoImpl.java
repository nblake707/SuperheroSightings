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
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;

/**
 *
 * @author blake
 */
public class SuperpowerDaoImpl implements SuperheroSightingsSuperpowerDao{
    

    //Prepared Statements for CRUD Superpower database table
    private static final String SQL_INSERT_SUPERPOWER
            = "INSERT INTO Superpowers "
            + "(Superpower, SuperpowerDescription)"
            + "values (?, ?);";
            
    private static final String SQL_SELECT_SUPERPOWER
            = "SELECT * FROM Superpowers where SuperpowerID = ?;";
            
    private static final String SQL_SELECT_ALL_SUPERPOWERS
            = "SELECT * FROM Superpowers";

    private static final String  SQL_UPDATE_SUPERPOWER
            = "UPDATE Superpowers set "
            + "Superpower = ?, SuperpowerDescription = ? "
            + "where SuperpowerID = ?;";
    
    private static final String SQL_DELETE_SUPERPOWER
            = "DELETE FROM Superpowers "
            + "where SuperpowerID = ?;";
    
    // Bridge table to join PersonSuperpowers with Superpowers table using SuperpowerID in PersonSuperpowers
    // One person may have more than 1 superpower so use personID to access all the superpowers
    private static final String SQL_SELECT_SUPERPOWERS_BY_PERSON_ID
            = "SELECT sp.SuperpowerID, sp.SuperpowerName, sp.SuperpowerDescription "
            + "FROM Superpowers sp "
            + "JOIN PersonSuperpowers persp on sp.SuperpowersID = persp.SuperpowersID  "
            + "where persp.PersonID = ?;";
            

    
       //Injecting Jdbdctemplate into code, to allow us to talk to sql database
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superpower createSuperpower(Superpower superpower) throws SuperheroSightingsPersistenceException {
        
         jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                 superpower.getSuperpowerName(),
                 superpower.getSuperpowerDescription());
      //Takes last ID created in database  & auto increments 
      //queryForObject
       int superpowerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
       superpower.setSuperpowerId(superpowerId);
        return superpower;
        
    }

    @Override
    public Superpower getSuperpowerById(int superpowerId) throws SuperheroSightingsPersistenceException {
        try {
        return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, new SuperpowerMapper(), superpowerId);
        } catch (EmptyResultDataAccessException ex) {
                return null;   
    }
    }

    @Override
    public List<Superpower> getAllSuperpowers() throws SuperheroSightingsPersistenceException {
               //instantiate new superpower
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    public void updateSuperpower(Superpower superpower) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                superpower.getSuperpowerName(),
                superpower.getSuperpowerDescription(),
                superpower.getSuperpowerId());
    }

    @Override
    public void deleteSuperpower(int superpowerId) throws SuperheroSightingsPersistenceException {
        //Delete superpower from superpower table in database 
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superpowerId);
    }

    @Override
    public List<Superpower> findSuperpowersForPerson(Person person) {
    return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_PERSON_ID , 
                              new SuperpowerMapper(), 
                              person.getPersonId());
    }
   
    
    //MAPPER
    //takes one row from database and turns into Superpower dto
    private static final class SuperpowerMapper implements RowMapper<Superpower>{

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
        //Naming in quotes should appear extact to sql database    
          Superpower suppow = new Superpower();
          suppow.setSuperpowerId(rs.getInt("SuperpowerID"));
          suppow.setSuperpowerName(rs.getString("Superpower"));
          suppow.setSuperpowerDescription(rs.getString("SuperpowerDescription"));
          
            return suppow;
        }
        
    }
    
    
}
