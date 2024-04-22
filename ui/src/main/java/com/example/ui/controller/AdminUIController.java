package com.example.ui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@Slf4j
@RequestMapping("/admin")
public class AdminUIController {
    // basic implementation based on: https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
    @Autowired
    AdminApiRestController adminApiRestController;

    @GetMapping("")
    public String adminPanel(Model model){
        model.addAttribute("users", adminApiRestController.getCustomers());
        return "admin-panel";
    }


}
