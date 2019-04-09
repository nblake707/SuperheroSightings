/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dto.Superpower;
import sg.thecodetasticfour.superherosightingsgroup.service.EntityNotFoundException;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsPersonServiceLayer;
import sg.thecodetasticfour.superherosightingsgroup.service.SuperheroSightingsSuperpowerServiceLayer;


@Controller
public class SuperpowerController {
    
   
    
    SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayer;
     SuperheroSightingsPersonServiceLayer personServiceLayer;
    
    @Inject
      public SuperpowerController(SuperheroSightingsSuperpowerServiceLayer superpowerServiceLayer, SuperheroSightingsPersonServiceLayer personServiceLayer) {
        this.superpowerServiceLayer = superpowerServiceLayer;
        this.personServiceLayer = personServiceLayer;
    }
    
    
       
       @RequestMapping(value="/superpowerHome", method=RequestMethod.GET)
   public String superpowerHome(HttpServletRequest request, Model model) throws SuperheroSightingsPersistenceException{
       
       List<Superpower> superpowerList = superpowerServiceLayer.getAllSuperpowers();
       model.addAttribute("superpowerList", superpowerList);

       return "/SuperpowerJSPs/Superpower"; 
   }
  
  
    @RequestMapping(value="/addSuperpower", method=RequestMethod.POST)
    public String addSuperpower(HttpServletRequest request){
       
       String theSuperpowerName = request.getParameter("superpowerName");
       String theSuperpowerDescription = request.getParameter("superpowerDescription");
               
       Superpower superpowerAdded = new Superpower();
       superpowerAdded.setSuperpowerName(theSuperpowerName);
       superpowerAdded.setSuperpowerDescription(theSuperpowerDescription);

       superpowerServiceLayer.createSuperpower(superpowerAdded);
       return "redirect:/superpowerHome";
       
   }
   
    @RequestMapping(value = "/displaySuperpowerDetails", method = RequestMethod.GET)
    public String displaySuperpowerDetails(HttpServletRequest request, Model model) throws EntityNotFoundException {
    
    String superpowerIdString = request.getParameter("superpowerId");
    int superpowerIdInt = Integer.parseInt(superpowerIdString);

    Superpower superpowerDetails = superpowerServiceLayer.getSuperpowerById(superpowerIdInt);
        
    model.addAttribute("superpowerDetails", superpowerDetails);

    return "/SuperpowerJSPs/superpowerDetails";
}
    
    @RequestMapping(value = "/displayEditSuperpowerForm", method = RequestMethod.GET)
    public String displayEditSuperpowerForm(HttpServletRequest request, Model model) throws EntityNotFoundException {
        
    String superpowerIdString = request.getParameter("superpowerId");
    int superpowerIdInt = Integer.parseInt(superpowerIdString);
    
    Superpower superpowerForEdit = superpowerServiceLayer.getSuperpowerById(superpowerIdInt);
    model.addAttribute("superpowerForEdit", superpowerForEdit);
    
    return "/SuperpowerJSPs/editSuperpower";
}
 
   
   @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(@ModelAttribute("superpower") Superpower superpower) {
    
     superpowerServiceLayer.updateSuperpower(superpower);

    return "redirect:/superpowerHome";
}
    
    
      @RequestMapping(value="/deleteSuperpower", method=RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request, Model model) throws EntityNotFoundException{

       String superpowerIdString = request.getParameter("superpowerId");
       int superpowerIdInt = Integer.parseInt(superpowerIdString);
       
       superpowerServiceLayer.deleteSuperpower(superpowerIdInt);
        
        return "redirect:/superpowerHome";
        
    }
   
    
}
