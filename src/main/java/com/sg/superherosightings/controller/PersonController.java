/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsOrganizationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;

@Controller
public class PersonController {

    SuperheroSightingsPersonServiceLayer personServiceLayer;
    SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayer;
    SuperheroSightingsOrganizationServiceLayer organizationServiceLayer;

    @Inject
    public PersonController(SuperheroSightingsPersonServiceLayer personServiceLayer, SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayer, SuperheroSightingsOrganizationServiceLayer organizationServiceLayer) {
        this.personServiceLayer = personServiceLayer;
        this.superpowerServiceLayer = superpowerServiceLayer;
        this.organizationServiceLayer = organizationServiceLayer;
    }

    @RequestMapping(value = "/personHome", method = RequestMethod.GET)
    public String personHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {

        List<Person> personList = personServiceLayer.getAllPersons();
        model.addAttribute("personList", personList);

        //needed to display dropdown options for superpowers
        List<Superpower> superpowerList = superpowerServiceLayer.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);

        //needed to display dropdown options for organizations
        List<Organization> organizationList = organizationServiceLayer.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);

        return "/PersonJSPs/Person";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(HttpServletRequest request) throws EntityNotFoundException {

        String personFirstName = request.getParameter("firstName");
        String personLastName = request.getParameter("lastName");
        String isHeroString = request.getParameter("isHero");
        //convert value to proper data type
        Boolean isHero = Boolean.parseBoolean(isHeroString);
        String personDescription = request.getParameter("personDescription");

        //List to put all the superpowers needed for person
        List<Superpower> superpowersForPerson = new ArrayList<>();
        //this arrayList will be used for drop down menu optins
        // when superpower is added to a person
        String[] superpowersMenuList = request.getParameterValues("personSuperpowers");
        for (String currentSuperpower : superpowersMenuList) {
            int superpowerIdInt = Integer.parseInt(currentSuperpower);
            Superpower sp = superpowerServiceLayer.getSuperpowerById(superpowerIdInt);
            superpowersForPerson.add(sp);
        }

        Person personAdded = new Person();
        personAdded.setFirstName(personFirstName);
        personAdded.setLastName(personLastName);
        personAdded.setIsHero(isHero);
        personAdded.setDescriptionOfPerson(personDescription);
        personAdded.setListOfSuperpowers(superpowersForPerson);//drop down menu superpowers

        personServiceLayer.createPerson(personAdded);

        return "redirect:/personHome";

    }

    @RequestMapping(value = "/displayPersonDetails", method = RequestMethod.GET)
    public String displayPersonDetails(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String personIdString = request.getParameter("personId");
        int personId = Integer.parseInt(personIdString);

        Person person = personServiceLayer.getPersonById(personId);
        
        //Get List of superpowers associated with the person object
        List<Superpower> personSuperpowers = person.getListOfSuperpowers();
        
        //Get List of organizations associated with the person object
        List<Organization> personOrganizations = person.getListOfOrganizations();

        //place the info on the model 
        model.addAttribute("person", person);
        model.addAttribute("personSuperpowers", personSuperpowers);
        model.addAttribute("personOrganizations", personOrganizations);

        //return info to the person details page
        return "/PersonJSPs/personDetails";

    }

    //the displayEditPersonForm url patter is mapped to this method
    //first edit endpoint
    @RequestMapping(value = "/displayEditPersonForm", method = RequestMethod.GET)
    public String displayEditPersonForm(HttpServletRequest request, Model model) throws EntityNotFoundException, SuperheroSightingsPersistenceException {

        //The Edit link for each entry in the table goes to the displayEditPersonForm 
        //controller endpoint and contains the contactId request parameter value associated with the entry.
        //grabbing that contactId value and converting it into an integer
        String personIdString = request.getParameter("personId");
        int personIdInt = Integer.parseInt(personIdString);

        //using the id number to ask the service layer to contact the dao for 
        //person object associated with the ID
        Person person = personServiceLayer.getPersonById(personIdInt);

        //placing the person object onto the model with a key of "editPerson"
        model.addAttribute("person", person);

        //needed to display dropdown options for superpowers
        List<Superpower> superpowerList = superpowerServiceLayer.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);

        //needed to display dropdown options for organizations
        List<Organization> organizationList = organizationServiceLayer.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);

        //Returns the logical name for the view containing the Edit Person form
        return "/PersonJSPs/editPerson";

    }


    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
    public String editPerson(@ModelAttribute("person") Person person, HttpServletRequest request) throws EntityNotFoundException {
           
        
        //List for all the superpowers needed for person
        List<Superpower> superpowersForPerson = new ArrayList<>();
        
        //List for all the organizations needed for person
        List<Organization> organizationsForPerson = new ArrayList<>();
     
        //used to grab values from the list of superpowers on the edit 
        // person page
       
        String[] superpowersMenuList = request.getParameterValues("personSuperpowers");
        for (String currentSuperpower : superpowersMenuList) {
            int superpowerIdInt = Integer.parseInt(currentSuperpower);
            Superpower sp = superpowerServiceLayer.getSuperpowerById(superpowerIdInt);
            superpowersForPerson.add(sp);
        }
        
        //repeating this process for the organizations list
        String[] organizationsMenuList = request.getParameterValues("personOrganizations");
        for (String currentOrganization : organizationsMenuList) {
            int organizationIdInt = Integer.parseInt(currentOrganization);
            Organization o = organizationServiceLayer.getOrganizationById(organizationIdInt);
            organizationsForPerson.add(o);
        }
        
        person.setListOfSuperpowers(superpowersForPerson);
        person.setListOfOrganizations(organizationsForPerson);
        personServiceLayer.updatePerson(person);

        return "redirect:/personHome";

    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String personIdString = request.getParameter("personId");
        int personIdInt = Integer.parseInt(personIdString);

        personServiceLayer.deletePerson(personIdInt);

        return "redirect:/personHome";
    }

}
