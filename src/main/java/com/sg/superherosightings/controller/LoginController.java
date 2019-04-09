package com.sg.superherosightings.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//@RequestMapping({"/login"})
//public class LoginController {
//        
//    public LoginController() {
//    }
//    
//    @RequestMapping(value="/displayLoginPage", method=RequestMethod.GET)
//    public String sayHi(Map<String, Object> model) {
//        model.put("message", "Hello from the login page!" );
//        return "Login/Login";
//    }
//}


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "Login/Login";
    }
}