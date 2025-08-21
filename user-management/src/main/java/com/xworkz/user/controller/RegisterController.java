package com.xworkz.user.controller;

import com.xworkz.user.dto.RegisterDTO;
import com.xworkz.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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

    @GetMapping("/redirectToLogin")
    public String getLoginPage() {
        System.out.println("redirect to login page");

        return "Login";
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
        RegisterDTO dto = null;
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

    @PostMapping("/setPassword")
    public String setPasswordInDB(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        System.out.println("Email: " + email);
        System.out.println("Password: " + password + "   ConfirmPassword :" + confirmPassword);
        if (registerService.setPasswordByEmail(email, password, confirmPassword)) {
            return "Login";
        }
        model.addAttribute("message", "Password mismatch");
        model.addAttribute("email", email);
        return "SetPassword";
    }

}
