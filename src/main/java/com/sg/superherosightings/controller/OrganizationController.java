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
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsOrganizationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;

@Controller
public class OrganizationController {

    @Inject
    SuperheroSightingsOrganizationServiceLayer organizationService;
    SuperheroSightingsPersonServiceLayer personServiceLayer;

    public OrganizationController(SuperheroSightingsOrganizationServiceLayer organizationService, SuperheroSightingsPersonServiceLayer personServiceLayer) {
        this.organizationService = organizationService;
        this.personServiceLayer = personServiceLayer;
    }

    @RequestMapping(value = "/organizationHome", method = RequestMethod.GET)
    public String organizationHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {

        List<Organization> organizationList = organizationService.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);

       
        List<Person> personList = personServiceLayer.getAllPersons();
        model.addAttribute("personList", personList);

        return "/OrganizationJSPs/Organization";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request, Model model) throws EntityNotFoundException {
        //grab incoming values from the form and create an organization object

        Organization organization = new Organization();

        organization.setOrganizationName(request.getParameter("organizationName"));
        organization.setOrganizationDescription(request.getParameter("organizationDescription"));
        organization.setOrganizationStreet(request.getParameter("organizationStreet"));
        organization.setOrganizationCity(request.getParameter("organizationCity"));
        organization.setOrganizationState(request.getParameter("organizationState"));
        organization.setOrganizationZipcode(request.getParameter("organizationZipCode"));
        organization.setOrganizationCountry(request.getParameter("organizationCountry"));
        String isHero = request.getParameter("isHero");
        organization.setIsItAHeroOrganization((Boolean.parseBoolean(isHero)));
         
        List<Person> people = new ArrayList<>();
        //users in the dropdown list
        String [] dropdownOptions = request.getParameterValues("personsSelectedByUser");
//        organization.setListOfPersons(dropdownOptions);

            for (String s:dropdownOptions){
               int ss = Integer.parseInt(s);
               Person p =personServiceLayer.getPersonById(ss);
               people.add(p);
    
}
       
      organization.setListOfPersons(people);

        organizationService.createOrganization(organization);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the organizationHome
        // so it can display the new Organization in the table.
        return "redirect:/organizationHome";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) throws EntityNotFoundException {
        String organizationIdString = request.getParameter("organizationId");
        int organizationIdInt = Integer.parseInt(organizationIdString);

        Organization organization = organizationService.getOrganizationById(organizationIdInt);

        model.addAttribute("organization", organization);

        List<Person> personsInOrganization = organization.getListOfPersons();
        model.addAttribute("personsInOrganization", personsInOrganization);

        return "/OrganizationJSPs/organizationDetails";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String organizationIdString = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdString);

        Organization organizationForEdit = organizationService.getOrganizationById(organizationId);
        model.addAttribute("organization", organizationForEdit);

        List<Person> people = personServiceLayer.getAllPersons();
        model.addAttribute("people", people);

        return "/OrganizationJSPs/editOrganization";
    }

    //  second edit endpoint
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@ModelAttribute("organization") Organization organization) {

        organizationService.updateOrganization(organization);

        return "redirect:/organizationHome";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {

        String organizationIdString = request.getParameter("organizantionId");

        int organizationId = Integer.parseInt(organizationIdString);

        organizationService.deleteOrganization(organizationId);

        return "redirect:/organizationHome";

    }
}
