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
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsOrganizationDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersonDao;
//import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsSuperpowerDao;
//import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
//
///**
// *
// * @author zissah
// */
//public class OrganizationDaoImplTest {
//    
//    SuperheroSightingsOrganizationDao orgDao;
//    SuperheroSightingsPersonDao perDao;
//    SuperheroSightingsSuperpowerDao suppowDao;
//    
//    public OrganizationDaoImplTest() {
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
//    orgDao = ctx.getBean("SuperheroSightingsOrganizationDao", SuperheroSightingsOrganizationDao.class);
//    perDao = ctx.getBean("SuperheroSightingsPersonDao", SuperheroSightingsPersonDao.class);
//    suppowDao = ctx.getBean("SuperheroSightingsSuperpowerDao", SuperheroSightingsSuperpowerDao.class);
//    
//        List <Organization> orgList = orgDao.getAllOrganizations();
//    for (Organization currentOrganization: orgList) {
//         //removes organization from database.
//        orgDao.deleteOrganization(currentOrganization.getOrganizationId());
//    }
//       
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class OrganizationDaoImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    }
//
//    /**
//     * Test of createOrganization method, of class OrganizationDaoImpl.
//     */
//    @Test
//    public void testCreateOrganization() throws Exception {
//    }
//
//    /**
//     * Test of getOrganizationById method, of class OrganizationDaoImpl.
//     */
//    @Test
//    public void testGetOrganizationById() throws Exception {
//    }
//
//    /**
//     * Test of getAllOrganizations method, of class OrganizationDaoImpl.
//     */
//    @Test
//    public void testGetAllOrganizations() throws Exception {
//    }
//
//    /**
//     * Test of updateOrganization method, of class OrganizationDaoImpl.
//     */
//    @Test
//    public void testUpdateOrganization() throws Exception {
//    }
//
//    /**
//     * Test of deleteOrganization method, of class OrganizationDaoImpl.
//     */
//    @Test
//    public void testDeleteOrganization() throws Exception {
//    }
//
//    /**
//     * Test of getOrganizationsForPerson method, of class OrganizationDaoImpl.
//     */
//    @Test
//    public void testGetOrganizationsForPerson() throws Exception {
//    }
//    
//}
