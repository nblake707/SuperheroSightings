///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.superherosightings.dao;
//
//import java.math.BigDecimal;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
//
///**
// *
// * @author zissah
// */
//public class LocationDaoImplTest {
//    
//    SuperheroSightingsLocationDao locDao;
//    
//    public LocationDaoImplTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() throws SuperheroSightingsPersistenceException {
//        ApplicationContext ctx
//   = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//       locDao = ctx.getBean("SuperheroSightingsLocationDao", SuperheroSightingsLocationDao.class);
//       
//        //Use the list to get list of all locations from database
//
//        List <Location> locationList = locDao.getAllLocations();
//        //Use for loop to get take one location from locationList into currentLocation
//        for (Location currentLocation : locationList) {
//       // remove all locations from the test database by going through the locationList and removing each one, until empty
//       locDao.deleteLocation(currentLocation.getLocationId()); 
//        }
//       
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    }
//
//    /**
//     * Test of createLocation method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testCreateLocation() throws Exception {
//        Location location = new Location();
//        
//        location.setLocationName("Macys");
//        location.setLocationDescription("Place for shopping");
//        location.setLocationCountry("USA");
//        location.setLocationState("New York");
//        location.setLocationCity("Manhattan");
//        location.setLocationStreet("34th Street");
//        location.setLocationZipcode("10001");
//        location.setLatitude(BigDecimal.ONE);
//        location.setLongitude(BigDecimal.ONE);
//        
//        locDao.createLocation(location);
//        //returnedLoc is supposed to be the same location being passsed by getLocationById as getLocationId
//        Location returnedLoc = locDao.getLocationById(location.getLocationId());
//        //comparing location to returnedLoc, if equal test passes.
//        assertEquals (location.getLocationId(),returnedLoc.getLocationId());
//    }
//
//    /**
//     * Test of getLocationById method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testGetLocationById() throws Exception {
//    }
//
//    /**
//     * Test of getAllLocations method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testGetAllLocations() throws Exception {
//    }
//
//    /**
//     * Test of updateLocation method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testUpdateLocation() throws Exception {
//    }
//
//    /**
//     * Test of deleteLocation method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testDeleteLocation() throws Exception {
//    }
//    
//}
