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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegisterController registerController;

    public LoginController()
    {
        System.out.println("LoginController constructor");
    }

    @GetMapping("/redirectToLogin")
    public String getLoginPage(Model model) {
        System.out.println("redirect to login page");
        System.out.println("getEmailsList method in controller");
        List<String> list=registerService.getAllEmail();
        model.addAttribute("emails",list);
        return "Login";
    }

    @PostMapping("/setPassword")
    public String setPasswordInDB(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        System.out.println("Email: " + email);
        System.out.println("Password: " + password + "   ConfirmPassword :" + confirmPassword);
        if (registerService.setPasswordByEmail(email, password, confirmPassword)) {
            return getLoginPage(model);
        }
        model.addAttribute("message", "Password mismatch");
        model.addAttribute("email", email);
        return "SetPassword";
    }

    @GetMapping("/redirectToUserDetails")
    public String getUserDetails(@RequestParam("userEmail")String email,Model model)
    {
        System.out.println("getUserDetails method in controller");
        RegisterDTO dto=registerService.getUserDetailsByEmail(email);
        model.addAttribute("dto",dto);
        return "LoginSuccess";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid RegisterDTO registerDTO, BindingResult bindingResult, Model model)
    {
        System.out.println("updateProfile method in controller");
        if (bindingResult.hasErrors()) {
            System.out.println("Errors in fields");
            bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + " : " + e.getDefaultMessage())
                    .forEach(System.out::println);

            model.addAttribute("message", "Invalid details");
            model.addAttribute("dto", registerDTO);
            return "UpdateProfile";
        }
        if(registerDTO.getProfilePic().isEmpty())
        {
            System.out.println("profile pic not changed");
        }
        else {
            try {
                byte[] bytes = registerDTO.getProfilePic().getBytes();
                Path path = Paths.get("D:\\Java\\File upload\\" + registerDTO.getUserName() + System.currentTimeMillis() + registerDTO.getProfilePic().getOriginalFilename());
                Files.write(path, bytes);
                registerDTO.setProfilePath(path.getFileName().toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if(registerService.updateUserDetails(registerDTO))
        {
            model.addAttribute("message","Profile Updated");
            registerDTO=registerService.getUserDetailsByEmail(registerDTO.getEmail());
            model.addAttribute("dto",registerDTO);
            return "LoginSuccess";
        }else {
            model.addAttribute("message","Invalid details");
            return "UpdateProfile";
        }
    }

    @GetMapping("editProfile")
    public String editProfile(@RequestParam("userEmail")String email,Model model)
    {
        System.out.println("editProfile method in controller");
        RegisterDTO dto=registerService.getUserDetailsByEmail(email);
        model.addAttribute("dto",dto);
        return "UpdateProfile";
    }

    @GetMapping("/deleteByEmail")
    public String deleteAccountByEmail(@RequestParam("email")String email,Model model)
    {
        System.out.println("delete account by email in controller");
        RegisterDTO registerDTO=new RegisterDTO();
        if(registerService.deleteAccountByEmail(email))
        {
            model.addAttribute("successMessage","Account deleted. create new user profile by register");
            return registerController.getRegisterPage();
        }else {
            model.addAttribute("message","Profile not deleted");
            registerDTO=registerService.getUserDetailsByEmail(registerDTO.getEmail());
            model.addAttribute("dto",registerDTO);
            return "LoginSuccess";
        }
    }

}
