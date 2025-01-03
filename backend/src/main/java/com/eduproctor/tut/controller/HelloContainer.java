package com.eduproctor.tut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@Controller //Tells the spring that its's a componenet and its a controller
@RestController // Tells java that its a restfull controller and it'll tell that its a component that always returns a response body
public class HelloContainer {

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    //Instead we can use @GetMapping instead of specifying method
    @GetMapping("/")
    public String hello(){
        return "Welcome to EduProctor by Bharath";
    }

}
