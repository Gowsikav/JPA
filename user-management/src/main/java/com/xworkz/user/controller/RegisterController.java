package com.xworkz.user.controller;

import com.xworkz.user.dto.RegisterDTO;
import com.xworkz.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    public RegisterController() {
        System.out.println("RegisterController constructor");
    }

    @GetMapping("/redirectToIndex")
    public String getIndex() {
        System.out.println("redirect to index page");
        return "index";
    }

    @GetMapping("/redirectToRegister")
    public String getRegisterPage() {
        System.out.println("redirect to register page");
        return "Register";
    }


    @PostMapping("/userRegister")
    public String getRegister(@Valid RegisterDTO registerDTO, BindingResult bindingResult, Model model) {
        System.out.println("getRegister in controller");
        System.out.println("controller data: " + registerDTO);
        if (bindingResult.hasErrors()) {
            System.out.println("Errors in fields");
            bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + " : " + e.getDefaultMessage())
                    .forEach(System.out::println);

            model.addAttribute("message", "Invalid details");
            model.addAttribute("dto", registerDTO);
            return "Register";
        }

        if (registerService.save(registerDTO)) {
            System.out.println("Data saved");
            model.addAttribute("email", registerDTO.getEmail());
            return "LoginWithOTP";
        } else {
            System.out.println("Data not saved");
            model.addAttribute("message", "Details not saved");
            model.addAttribute("dto", registerDTO);
            return "Register";
        }

    }

    @PostMapping("/userLogin")
    public String getLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        System.out.println("getLogin method in controller");
        System.out.println("Email: " + email + "   " + "password: " + password);
        RegisterDTO dto;
        try {
            dto = registerService.getUserDetails(email, password);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            System.out.println(e.getMessage());
            return "Login";
        }
        if (dto == null) {
            model.addAttribute("message", "Password  incorrect");
            model.addAttribute("email", email);
            System.out.println("Details not found");
            return "Login";
        }
        if (dto.getLoginCount() == -1) {
            model.addAttribute("email", dto.getEmail());
            return "SetPassword";
        }
        model.addAttribute("dto", dto);
        model.addAttribute("message","Login Successfully");
        System.out.println("Found details");
        return "LoginSuccess";
    }

    @PostMapping("/userLoginWithOTP")
    public String getLoginWithOTP(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        System.out.println("Email: " + email + "  otp: " + otp);
        if (registerService.compareOtp(email, otp)) {
            System.out.println("Otp is verified");
            model.addAttribute("email", email);
            return "SetPassword";
        }
        model.addAttribute("message", "Otp not matched");
        model.addAttribute("email", email);
        return "LoginWithOTP";
    }



    @PostMapping("/resend-otp")
    @ResponseBody
    public String resendOTP(@RequestParam("email")String email)
    {
        System.out.println("resend otp in controller");
        if(registerService.setOTPByEmail(email))
        {
            return "Otp send to your email";
        }
        return "Otp not sent";
    }

    @GetMapping("/redirectToFindDetails")
    public String getEmailForFindDetailsPage(Model model)
    {
        System.out.println("getEmailForFindDetailsPage method in controller");
        System.out.println("getEmailsList method in controller");
        List<String> list=registerService.getAllEmail();
        model.addAttribute("emails",list);
        return "FindDetails";
    }

    @GetMapping("/getUserByEmail")
    @ResponseBody
    public RegisterDTO getDetailsByEmailForFindDetailsPage(@RequestParam("email")String email)
    {
        System.out.println("getDetailsByEmailForFindDetailsPage method in controller");
        RegisterDTO dto=registerService.getUserDetailsByEmail(email);
        System.out.println(dto);
        return dto;
    }
}
