package com.xworkz.library.controller;

import com.xworkz.library.dto.BookRegisterDTO;
import com.xworkz.library.service.BookRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class LibraryController {

    @Autowired
    private BookRegisterService service;

    public LibraryController() {
        System.out.println("LibraryController constructor");
    }

    @GetMapping("/redirectToRegister")
    public String getRegisterPage() {
        System.out.println("redirect to book register page");
        return "BookRegister";
    }

    @GetMapping("/redirectToIndex")
    public String getHomePage() {
        System.out.println("redirect to home page");
        return "index";
    }

    @PostMapping("/bookRegister")
    public String registerBook(BookRegisterDTO dto, Model model) {
        System.out.println("Register book method in controller");
        System.out.println("controller data: " + dto);
        if (service.save(dto)) {
            System.out.println("Details saved");
            return getAllData(model);
        } else {
            System.out.println("Details not saved");
            model.addAttribute("message", "Invalid details");
        }
        return "BookRegister";
    }

    @GetMapping("/redirectToViewAll")
    public String getAllData(Model model)
    {
        System.out.println("get all data in controller");
        List<BookRegisterDTO> dtoList=service.getAll();
        model.addAttribute("dto",dtoList);
        model.addAttribute("successMessage","All Details");
        return "DisplayAllData";
    }

    @GetMapping("/view")
    public String getSingleData(@RequestParam("id")Integer id, Model model)
    {
        System.out.println("getSingleData method in controller");
        BookRegisterDTO dto=service.getSingleDataById(id);
        model.addAttribute("ref",dto);
        model.addAttribute("message","Book details");
        return "ViewSingleData";
    }

    @GetMapping("/edit")
    public String editDataById(@RequestParam("id")Integer id, Model model)
    {
        System.out.println("updateDataById method in controller");
        BookRegisterDTO dto=service.getSingleDataById(id);
        model.addAttribute("dto",dto);
        System.out.println("redirect to update page");
        return "UpdateData";
    }

    @PostMapping("/updateBook")
    public String updateBookData(BookRegisterDTO dto,Model model)
    {
        System.out.println("updateBookData method in controller");
        System.out.println("update data controller: "+dto);
        if(service.updateDataById(dto))
        {
            System.out.println("Data updated");
            List<BookRegisterDTO> dtoList=service.getAll();
            model.addAttribute("dto",dtoList);
            model.addAttribute("successMessage","All Details");
            return "DisplayAllData";
        }else {
            System.out.println("Data not updated");
            model.addAttribute("Invalid details");
            return "UpdateData";
        }
    }

    @GetMapping("/delete")
    public String deleteDataById(@RequestParam("id")Integer id, Model model)
    {
        System.out.println("deleteDataById method in controller");
        if(service.deleteDataById(id))
        {
            System.out.println("Data deleted");
            model.addAttribute("successMessage","All Details");
        }else {
            System.out.println("Data not deleted");
            model.addAttribute("message","Data not deleted");
        }
        List<BookRegisterDTO> dtoList=service.getAll();
        model.addAttribute("dto",dtoList);
        return "DisplayAllData";
    }
}
