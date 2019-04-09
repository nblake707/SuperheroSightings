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
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
//
///**
// *
// * @author zissah
// */
//public class SuperpowerDaoImplTest {
//    
//    SuperheroSightingsSuperpowerDao suppowDao;
//            
//    public SuperpowerDaoImplTest() {
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
//        //Inject Dao bean, w/out won't be able to run a test.
//         ApplicationContext ctx
//   = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//   suppowDao = ctx.getBean("SuperheroSightingsSuperpowerDao", SuperheroSightingsSuperpowerDao.class);
//   
//    List <Superpower> superpowerList = suppowDao.getAllSuperpowers();
//    for (Superpower currentSuperpower: superpowerList) {
//        //removes ever superpower from database.
//       suppowDao.deleteSuperpower(currentSuperpower.getSuperpowerId());
//    }
//  
//}
//    
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJbdcTemplate method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testSetJbdcTemplate() {
//    }
//
//    /**
//     * Test of createSuperpower method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testCreateSuperpower() throws Exception {
//        //Instaniate Superpower DTO
//        Superpower superpower = new Superpower();
//        //Use setter methods
//        superpower.setSuperpowerName("Speed");
//        superpower.setSuperpowerDescription("Extremely fast");
//        
//        suppowDao.createSuperpower(superpower);
//        Superpower returnedSPDao = suppowDao.getSuperpowerById(superpower.getSuperpowerId());
//        
//        assertEquals (superpower, returnedSPDao);
//    }
//
//    /**
//     * Test of getSuperpowerById method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testGetSuperpowerById() throws Exception {
//    }
//
//    /**
//     * Test of getAllSuperpowers method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testGetAllSuperpowers() throws Exception {
//        
//    }
//
//    /**
//     * Test of updateSuperpower method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testUpdateSuperpower() throws Exception {
//    }
//
//    /**
//     * Test of deleteSuperpower method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testDeleteSuperpower() throws Exception {
//    }
//    
//}
