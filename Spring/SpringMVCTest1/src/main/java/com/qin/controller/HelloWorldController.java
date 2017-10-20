package com.qin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class HelloWorldController {
 
    @RequestMapping("/user/hello.html")
    public String hello(Model model, @RequestParam(value="id") String id) {       
    	System.out.println(id);       
    	model.addAttribute("greeting", "Hello Spring MVC");         
        return"helloworld";         
    } 
}