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
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
//
///**
// *
// * @author zissah
// */
//public class PersonDaoImplTest {
//    
//    SuperheroSightingsPersonDao perDao;
//    
//    public PersonDaoImplTest() {
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
//                        ApplicationContext ctx
//   = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//       perDao = ctx.getBean("SuperheroSightingsPersonDao", SuperheroSightingsPersonDao.class);
//       
//           List <Person> personList = perDao.getAllPersons();
//    for (Person currentPerson: personList) {
//        //removes ever superpower in database.
//       perDao.deletePerson(currentPerson.getPersonId());
//    }
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class PersonDaoImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    }
//
//    /**
//     * Test of createPerson method, of class PersonDaoImpl.
//     */
//    @Test
//    public void testCreatePerson() throws Exception {
//    }
//
//    /**
//     * Test of getPersonById method, of class PersonDaoImpl.
//     */
//    @Test
//    public void testGetPersonById() throws Exception {
//    }
//
//    /**
//     * Test of getAllPersons method, of class PersonDaoImpl.
//     */
//    @Test
//    public void testGetAllPersons() throws Exception {
//    }
//
//    /**
//     * Test of updatePerson method, of class PersonDaoImpl.
//     */
//    @Test
//    public void testUpdatePerson() throws Exception {
//    }
//
//    /**
//     * Test of deletePerson method, of class PersonDaoImpl.
//     */
//    @Test
//    public void testDeletePerson() throws Exception {
//    }
//    
//}
