package com.example.ui.controller;

import com.example.ui.entity.CustomerResponse;
import com.example.ui.entity.Error;
import com.example.ui.entity.LoginAttempt;
import com.example.ui.entity.LoginAttemptResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

//@RestController
@Controller
@ControllerAdvice
@Slf4j
public class UIController {
    // basic implementation based on: https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
    @Autowired
    APIGatewayRestController apiGatewayRestController;

    private final String USER_NOT_LOGGED_IN = "SESSION_TOKEN_COOKIE_NOT_SET";

    @ModelAttribute("user")
    public CustomerResponse populateUser(HttpSession session){
        CustomerResponse customer = (CustomerResponse) session.getAttribute("user");
        return customer;
    }

    @ModelAttribute("error")
    public Error populateError(){
        return new Error("userErrorMsg", "devErrorMsg");
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

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private RedirectView loginAttempt(@RequestParam String username, @RequestParam String password, HttpServletResponse response, HttpSession session){
        LoginAttempt loginAttempt = new LoginAttempt(username, password);
        System.out.println(loginAttempt.getUsername());
        LoginAttemptResponse gatewayResponse = apiGatewayRestController.verifyLoginAttempt(loginAttempt);
        System.out.println(gatewayResponse.getSessionToken());
        if(!gatewayResponse.isSuccessfulLogin()){
            return new RedirectView("/login");
        }
        //set the user for this session
        CustomerResponse customerResponse= gatewayResponse.getCustomer();
        if(customerResponse != null){
            session.setAttribute("user", customerResponse);
        }
        //create cookie
        String sessionToken = gatewayResponse.getSessionToken();
        Cookie sessionCookie = createSessionCookie(sessionToken);
        //add to the response
        response.addCookie(sessionCookie);
        return new RedirectView("/");
    }

    @GetMapping("/logout")
    private RedirectView logout(HttpServletResponse response, RedirectAttributes redirectAttributes, HttpSession session){
        if(!apiGatewayRestController.logout()){
            redirectAttributes.addFlashAttribute("error", new Error("could not log out", "gateway returned false for logout, could not end session"));
            return new RedirectView("/exception");
        }
        //remove user from session
        session.setAttribute("user", null);
        //create reset cookie
        Cookie usernameCookie = resetSessionCookie();
        //add to the response
        response.addCookie(usernameCookie);
        return new RedirectView("/login");
    }

    /**
     * Mapping to error page for user, to not overwrite the /error default, which is useful for development
     * @return
     */
    @GetMapping("/exception")
    private String error(){
        return "error-ui";
    }

    private Cookie createSessionCookie(String sessionToken){
        Cookie usernameCookie = new Cookie("sessionToken", sessionToken);
        usernameCookie.setMaxAge(86400);
        usernameCookie.setSecure(false);
        usernameCookie.setHttpOnly(false);
        usernameCookie.setPath("/");
        return usernameCookie;
    }

    private Cookie resetSessionCookie(){
        return createSessionCookie(USER_NOT_LOGGED_IN);
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
