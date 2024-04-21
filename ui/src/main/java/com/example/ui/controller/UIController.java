package com.example.ui.controller;

import com.example.ui.entity.LoginAttempt;
import com.example.ui.entity.LoginAttemptResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//@RestController
@Controller
@ControllerAdvice
@Slf4j
public class UIController {
    // basic implementation based on: https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
    @Autowired
    APIGatewayRestController apiGatewayRestController;

    private final String USER_NOT_LOGGED_IN = "USERNAME_COOKIE_NOT_SET";

    @ModelAttribute("username")
    public String populateUsername(@CookieValue(name = "username", defaultValue = USER_NOT_LOGGED_IN) String username){
        return username;
    }

    @GetMapping("")
    private String index(Model model){
        return "index";
    }

    @GetMapping("/login")
    private String login(Model model) {
        model.addAttribute("loginAttempt", new LoginAttempt());
        return "login";
    }

    @PostMapping("/login")
    private RedirectView loginAttempt(@ModelAttribute LoginAttempt loginAttempt, HttpServletResponse response){
        System.out.println(loginAttempt);
        LoginAttemptResponse gatewayResponse = apiGatewayRestController.verifyLoginAttempt(loginAttempt);
        if(!gatewayResponse.isSuccessfulLogin()){
            return new RedirectView("/login");
        }
        //create cookie
        Cookie usernameCookie = createUsernameCookie(loginAttempt.getUsername());
        //add to the response
        response.addCookie(usernameCookie);
        return new RedirectView("/");
    }

    @GetMapping("/logout")
    private RedirectView logout(HttpServletResponse response){
        apiGatewayRestController.logout();
        //create reset cookie
        Cookie usernameCookie = resetUsernameCookie();
        //add to the response
        response.addCookie(usernameCookie);
        return new RedirectView("/login");
    }

    private Cookie createUsernameCookie(String username){
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setMaxAge(86400);
        usernameCookie.setSecure(false);
        usernameCookie.setHttpOnly(false);
        usernameCookie.setPath("/");
        return usernameCookie;
    }

    private Cookie resetUsernameCookie(){
        return createUsernameCookie(USER_NOT_LOGGED_IN);
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
