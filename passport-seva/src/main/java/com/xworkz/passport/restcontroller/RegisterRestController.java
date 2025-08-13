package com.xworkz.passport.restcontroller;

import com.xworkz.passport.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RegisterRestController {

    @Autowired
    private PassportService service;

    @GetMapping("/loginEmail")
    public String getEmail(@RequestParam("email") String email)
    {
        System.out.println("getEmail method in rest controller");
        String existingEmail=service.findExistingEmail(email);
        System.out.println("send email: "+email);
        System.out.println("Existing email: "+existingEmail);
        if(existingEmail==null)
        {
            return " ";
        }
        return "Email already exists";
    }

    @GetMapping("/phoneNumberCheck")
    public String getPhoneNumber(@RequestParam("phoneNumber") Long phoneNumber)
    {
        System.out.println("getPhoneNumber method in rest controller");
        Long existingPhoneNumber=service.findExistingPhoneNumber(phoneNumber);
        System.out.println("send phone number: "+phoneNumber);
        System.out.println("Existing phone number: "+existingPhoneNumber);
        if(existingPhoneNumber==null)
        {
            return " ";
        }
        return "Phone Number already exists";
    }

    @GetMapping("/loginIdCheck")
    public String getLoginId(@RequestParam("loginId") String login)
    {
        System.out.println("getLoginId method in rest controller");
        String loginId=service.findExistingLoginId(login);
        System.out.println("send login: "+login);
        System.out.println("Existing LoginId: "+loginId);
        if(loginId==null)
        {
            return " ";
        }
        return "LoginId already exists";
    }
}
