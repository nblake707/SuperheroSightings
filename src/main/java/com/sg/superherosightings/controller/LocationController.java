/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Location;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsLocationServiceLayer;


@Controller
public class LocationController {

    SuperheroSightingsLocationServiceLayer locationServiceLayer;

    @Inject
    public LocationController(SuperheroSightingsLocationServiceLayer locationServiceLayer) {
        this.locationServiceLayer = locationServiceLayer;
    }


    @RequestMapping(value = "/locationHome", method = RequestMethod.GET)
    public String locationHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException {

        List<Location> locationList = locationServiceLayer.getAllLocations();
        model.addAttribute("locationList", locationList);

        return "/LocationJSPs/Location";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request) throws SuperheroSightingsPersistenceException, EntityNotFoundException {

        String locName = request.getParameter("locationName");
        String locDescription = request.getParameter("locationDescription");
        String locCountry = request.getParameter("locationCountry");
        String locState = request.getParameter("locationState");
        String locCity = request.getParameter("locationCity");
        String locStreet = request.getParameter("locationStreet");
        String locZipcode = request.getParameter("locationZipCode");
        String locLatitudeString = request.getParameter("latitude");
        String locLongitudeString = request.getParameter("longitude");

        BigDecimal locLatitude = new BigDecimal(locLatitudeString).setScale(4, RoundingMode.HALF_UP);
        BigDecimal locLongitude = new BigDecimal(locLongitudeString).setScale(4, RoundingMode.HALF_UP);

        // persist the new Location
        Location locAdded = new Location();
        locAdded.setLocationName(locName);
        locAdded.setLocationDescription(locDescription);
        locAdded.setLocationCountry(locCountry);
        locAdded.setLocationState(locState);
        locAdded.setLocationCity(locCity);
        locAdded.setLocationStreet(locStreet);
        locAdded.setLocationZipcode(locZipcode);
        locAdded.setLatitude(locLatitude);
        locAdded.setLongitude(locLongitude);

        locationServiceLayer.createLocation(locAdded);
        //redirect goes back to the browser rather than JSP
        return "redirect:/locationHome";
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) throws EntityNotFoundException {
       String locationIdString = request.getParameter("locationId");
       int locationId = Integer.parseInt(locationIdString);
       
       Location location = locationServiceLayer.getLocationById(locationId);
       model.addAttribute("location",location);
          

        // Return the logical name of our View component
        return "/LocationJSPs/locationDetails";
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String locationIdString = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdString);

        Location locationForEdit = locationServiceLayer.getLocationById(locationId);
        model.addAttribute("locationForEdit", locationForEdit);

        return "/LocationJSPs/editLocation";
    }

//   Edit Location form submission endpoint
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editContact(@ModelAttribute("location") Location location) {

        locationServiceLayer.updateLocation(location);

        return "redirect:locationHome";
    }

    
       @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) throws EntityNotFoundException {

        String locationIdString = request.getParameter("locationId");

        int locationIdInt = Integer.parseInt(locationIdString);

        locationServiceLayer.deleteLocation(locationIdInt);

        return "redirect:/locationHome";

    }
}
