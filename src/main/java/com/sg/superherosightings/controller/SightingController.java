/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.dto.Person;
import sg.thecodetasticfour.superherosightingsgroup.dto.Sighting;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsLocationServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSightingServiceLayer;

@Controller
public class SightingController {

    SuperheroSightingsSightingServiceLayer sightingServiceLayer;
    SuperheroSightingsPersonServiceLayer personServiceLayer;
    SuperheroSightingsLocationServiceLayer locationServiceLayer;

    @Inject

    public SightingController(SuperheroSightingsSightingServiceLayer sightingServiceLayer, SuperheroSightingsPersonServiceLayer personServiceLayer, SuperheroSightingsLocationServiceLayer locationServiceLayer) {
        this.sightingServiceLayer = sightingServiceLayer;
        this.personServiceLayer = personServiceLayer;
        this.locationServiceLayer = locationServiceLayer;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainHomePage(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException, EntityNotFoundException {

        List<Sighting> latestSightings = sightingServiceLayer.getLatestTenSightings();

        List<Sighting> sightingList = new ArrayList<>();
        for (Sighting currentSighting : latestSightings) {
            //
            Sighting sighting = currentSighting;
            int personId = currentSighting.getPersonId();
            int locationId = currentSighting.getLocationId();
            Person person = personServiceLayer.getPersonById(personId);
            Location location = locationServiceLayer.getLocationById(locationId);
            sighting.setPerson(person);
            sighting.setLocation(location);
            sightingList.add(sighting);
        }
        model.addAttribute("Sightings", sightingList);

        return "index";
    }

    @RequestMapping(value = "/sightingHome", method = RequestMethod.GET)
    public String sightingHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException, EntityNotFoundException {

        List<Person> personList = personServiceLayer.getAllPersons();
        model.addAttribute("personList", personList);

        List<Location> locationList = locationServiceLayer.getAllLocations();
        model.addAttribute("locationList", locationList);

        List<Sighting> sightings = sightingServiceLayer.getAllSightings();

        List<Sighting> sightingList = new ArrayList<>();
        for (Sighting currentSighting : sightings) {
            Sighting sighting = currentSighting;
            int prsnID = currentSighting.getPersonId();
            int locID = currentSighting.getLocationId();
            Person person = personServiceLayer.getPersonById(prsnID);
            Location location = locationServiceLayer.getLocationById(locID);
            sighting.setPerson(person);
            sighting.setLocation(location);
            sightingList.add(sighting);
        }
        model.addAttribute("Sightings", sightingList);

        return "/Sighting/Sighting";
    }

    @RequestMapping(value = "/addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request, Model model) throws EntityNotFoundException {

        Sighting sighting = new Sighting();

        String isHero = request.getParameter("isHero");
        sighting.setIsHeroSighting((Boolean.parseBoolean(isHero)));

        String[] personDropdown = request.getParameterValues("personSelectedByUser");
        for (String dd : personDropdown) {
            int d = Integer.parseInt(dd);
            Person p = personServiceLayer.getPersonById(d);
            sighting.setPerson(p);
        }

        String[] locationDropdown = request.getParameterValues("locationSelectedByUser");
        for (String dd : locationDropdown) {
            int d = Integer.parseInt(dd);
            Location l = locationServiceLayer.getLocationById(d);
            sighting.setLocation(l);
        }

        String sightingDateString = request.getParameter("sightingDate");
        DateTimeFormatter formatSighting = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate dateAndTime = LocalDate.parse(sightingDateString);
        sighting.setJustTheSightingDate(dateAndTime);

        sightingServiceLayer.createSighting(sighting);

        return "redirect:/sightingHome";

    }

    ///need to add person list and location list
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(Model model) {
        // Get all the Sighting from the Service Layer
        List<Sighting> sightingList = sightingServiceLayer.getAllSightings();
        // Add the List of Sightings on the Model
        model.addAttribute("sightingList", sightingList);
        // Return the logical name of our View component
        return "/Sighting/sightingDetails";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String sightingIdString = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdString);
        Sighting sighting = sightingServiceLayer.getSightingById(sightingId);
        model.addAttribute("sighting", sighting);

        List<Person> allPersons = personServiceLayer.getAllPersons();
        model.addAttribute("allPersons", allPersons);

        List<Location> allLocations = locationServiceLayer.getAllLocations();
        model.addAttribute("allLocations", allLocations);

        return "/Sighting/editSighting";
    }

//   Second Edit Endpoint
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, @ModelAttribute("sighting") Sighting sighting) throws EntityNotFoundException {

//        
//         String sightingDateString = request.getParameter("sightingDate");
//       DateTimeFormatter formatSighting = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDate dateAndTime = LocalDate.parse(sightingDateString);
//         sighting.setJustTheSightingDate(dateAndTime);
//        
        //Grabs the person selected on the dropdown menu
        String[] peopleMenuList = request.getParameterValues("people");
        for (String currentPerson : peopleMenuList) {
            int personIdInt = Integer.parseInt(currentPerson);
            Person p = personServiceLayer.getPersonById(personIdInt);
            sighting.setPerson(p);
        }

        //grabs the location selected on the dropdown menu
        String[] locationMenuList = request.getParameterValues("locations");
        for (String currentLocation : locationMenuList) {
            int locationIdInt = Integer.parseInt(currentLocation);
            Location l = locationServiceLayer.getLocationById(locationIdInt);
            sighting.setLocation(l);
        }

        sightingServiceLayer.updateSighting(sighting);

        return "redirect:/sightingHome";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String sightingIdString = request.getParameter("sightingId");
        int sightingIdInt = Integer.parseInt(sightingIdString);

        sightingServiceLayer.deleteSighting(sightingIdInt);

        return "redirect:/sightingHome";

    }

    ///Deleted from editSighting jsp
//    <div class="form-group">
//                    <label for="add-sightingDate" class="col-md-4 control-label">Sighting Date</label>
//                    <div class="col-md-8">
//                        <sf:input type="text" class="form-control" id="add-sighting-Date"
//                                  path="justTheSightingDate" placeholder="Sighting Date"/>
//                        <sf:errors path="justTheSightingDate" cssclass="error"></sf:errors>
//                        </div>
//                    </div>
}
