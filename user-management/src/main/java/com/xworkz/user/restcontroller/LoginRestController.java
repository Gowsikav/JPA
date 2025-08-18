package com.xworkz.user.restcontroller;

import com.xworkz.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginRestController {

    @Autowired
    private RegisterService service;
    public LoginRestController()
    {
        System.out.println("LoginRestController constructor");
    }

    @GetMapping("/loginEmailCheck")
    public String checkEmail(@RequestParam("email")String email)
    {
        System.out.println("checkEmail method in rest controller");
        String existingEmail=service.checkExistingEmail(email);
        System.out.println("Existing email: "+existingEmail);
        if(!email.equalsIgnoreCase(existingEmail))
            return "";
        return "Correct email";
    }
}
