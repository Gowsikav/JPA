package com.xworkz.passport.controller;

import com.xworkz.passport.dto.PassportDTO;
import com.xworkz.passport.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class PassportController {

    @Autowired
    private PassportService passportService;

    public PassportController()
    {
        System.out.println("PassportController constructor");
    }

    @GetMapping("/redirectToPassport")
    public String getRegisterPage()
    {
        System.out.println("redirect to user register page");
        return "PassportRegister";
    }

    @GetMapping("/getIndex")
    public String getIndex()
    {
        System.out.println("redirect to index page");
        return "index";
    }

    @PostMapping("/register")
    public String getRegister(@Valid PassportDTO passportDTO, BindingResult bindingResult, Model model)
    {
        System.out.println("getRegister method in controller");
        System.out.println("Controller data: "+passportDTO);
        if(bindingResult.hasErrors())
        {
            System.out.println("field has error");
            bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField()+" -> "+fieldError.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("message","Invalid Details");
            model.addAttribute("dto",passportDTO);
            return "PassportRegister";
        }
        if(passportService.save(passportDTO))
        {
            model.addAttribute("successMessage","Details saved");
            System.out.println("details valid");

        }else {
            System.out.println("details not valid");
            model.addAttribute("message","Details not saved");
            model.addAttribute("dto",passportDTO);
        }
        return findAll(model);
    }

    @GetMapping("/getAllEntity")
    public String findAll(Model model)
    {
        System.out.println("findAll method in controller");
        List<PassportDTO> list=passportService.findAll();
        model.addAttribute("list",list);
        return "GetAllData";
    }

    @GetMapping("/view")
    public String findById(@RequestParam("id")Integer id,Model model)
    {
        System.out.println("findById method in controller");
        PassportDTO dto=passportService.findByPassportId(id);
        model.addAttribute("ref",dto);
        return "DisplayUserDetails";
    }

    @GetMapping("/edit")
    public String editPassport(@RequestParam("id")Integer id,Model model)
    {
        System.out.println("editPassport method in controller");
        PassportDTO dto=passportService.findByPassportId(id);
        model.addAttribute("dto",dto);
        return "UpdatePassportRegister";
    }

    @PostMapping("/updateUser")
    public String updateUserPassportDetails(@Valid PassportDTO passportDTO,BindingResult bindingResult,Model model)
    {
        System.out.println("updateUserPassportDetails method in controller");
        if(bindingResult.hasErrors())
        {
            System.out.println("field has error");
            bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField()+" -> "+fieldError.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("message","Invalid Details");
            model.addAttribute("dto",passportDTO);
            return "UpdatePassportRegister";
        }
        String updated=passportService.updatePassportById(passportDTO);
        System.out.println("Update: "+updated);
        List<PassportDTO> list=passportService.findAll();
        model.addAttribute("list",list);
        return "GetAllData";
    }

    @GetMapping("/delete")
    public String deletePassportDetails(@RequestParam("id")Integer id,Model model)
    {
        System.out.println("deletePassportDetails method in controller");
        String delete=passportService.deleteUserById(id);
        System.out.println("delete: "+delete);
        List<PassportDTO> list=passportService.findAll();
        model.addAttribute("list",list);
        return "GetAllData";
    }

    @GetMapping("/search")
    public String searchUserDetailsById(@RequestParam("userName") String userName,Model model)
    {
        System.out.println("searchUserDetailsById method in controller");
        List<PassportDTO> list=passportService.searchUserByUserName(userName);
        if(list.isEmpty())
        {
            model.addAttribute("message","Details not found");
        }
        model.addAttribute("list",list);
        return "GetAllData";
    }
}
