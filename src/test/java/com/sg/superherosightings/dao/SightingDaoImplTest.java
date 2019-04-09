///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.superherosightings.dao;
//
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsLocationDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSightingDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
//
///**
// *
// * @author zissah
// */
//public class SightingDaoImplTest {
//    SuperheroSightingsSightingDao sightDao;
//    SuperheroSightingsSuperpowerDao suppowDao;
//    SuperheroSightingsPersonDao perDao;
//    SuperheroSightingsLocationDao locDao;
//    
//    
//    public SightingDaoImplTest() {
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
//               ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//       sightDao = ctx.getBean("SuperheroSightingsSightingDao", SuperheroSightingsSightingDao.class);
//       suppowDao = ctx.getBean("SuperheroSightingsSuperpowerDao", SuperheroSightingsSuperpowerDao.class);
//       perDao = ctx.getBean("SuperheroSightingsPersonDao", SuperheroSightingsPersonDao.class);
//       locDao = ctx.getBean("SuperheroSightingsLocationDao", SuperheroSightingsLocationDao.class);
//       
//       List <Sighting> sightingList = sightDao.getAllSightings();
//       for(Sighting currentSighting : sightingList) {
//           sightDao.deleteSighting(currentSighting.getSightingId());
//       }
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    }
//
//    /**
//     * Test of createSighting method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testCreateSighting() throws Exception {
//    }
//
//    /**
//     * Test of getSightingById method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testGetSightingById() throws Exception {
//    }
//
//    /**
//     * Test of getAllSightings method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testGetAllSightings() throws Exception {
//    }
//
//    /**
//     * Test of updateSighting method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testUpdateSighting() throws Exception {
//    }
//
//    /**
//     * Test of deleteSighting method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testDeleteSighting() throws Exception {
//    }
//
//    /**
//     * Test of getLatestTenSightings method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testGetLatestTenSightings() throws Exception {
//    }
//
//    /**
//     * Test of getAllSightingsByLocation method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testGetAllSightingsByLocation() throws Exception {
//    }
//
//    /**
//     * Test of getAllSightingsByDate method, of class SightingDaoImpl.
//     */
//    @Test
//    public void testGetAllSightingsByDate() throws Exception {
//    }
//    
//}
