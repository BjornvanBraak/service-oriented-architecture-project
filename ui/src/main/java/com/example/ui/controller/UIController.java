package com.example.ui.controller;

import com.example.ui.entity.LoginAttempt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
@Controller
@ControllerAdvice
@Slf4j
public class UIController {
    // https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
    @GetMapping("")
    private String index(){
        return "index";
    }

    @GetMapping("/login")
    private String login(Model model, @ModelAttribute LoginAttempt loginAttempt) {
        model.addAttribute("loginAttempt", loginAttempt);
        return "login";
    }
//    private Map<Long, Employee> employeeMap = new HashMap<>();
//
//    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
//    public String submit(
//            @ModelAttribute("employee") Employee employee,
//            BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//        model.addAttribute("name", employee.getName());
//        model.addAttribute("id", employee.getId());
//
//        employeeMap.put(employee.getId(), employee);
//
//        return "employeeView";
//    }
//
//    @ModelAttribute
//    public void addAttributes(Model model) {
//        model.addAttribute("msg", "Welcome to the Netherlands!");
//    }
}
