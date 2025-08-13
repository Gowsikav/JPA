package com.xworkz.bookstore.controller;

import com.xworkz.bookstore.dto.BookStoreDTO;
import com.xworkz.bookstore.service.BookStoreService;
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
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;

    public BookStoreController()
    {
        System.out.println("BookStoreController constructor");
    }

    @GetMapping("/redirectToTourism")
    public String getBookPage()
    {
        System.out.println("redirected to book form page");
        return "BookStore";
    }

    @GetMapping("/getIndex")
    public String getIndex()
    {
        System.out.println("returned index  page");
        return "index";
    }

    @PostMapping("/bookstore")
    public String getBook(@Valid BookStoreDTO dto, BindingResult bindingResult, Model model)
    {
        System.out.println("getBook method in controller");
        System.out.println("controller data: "+dto);
        if(bindingResult.hasErrors())
        {
            System.out.println("Error in fields");
            bindingResult.getFieldErrors().stream()
                    .map(e->e.getField()+" : "+e.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("message","Invalid details");
            model.addAttribute("dto",dto);
            return "BookStore";
        }
        System.out.println("All details are valid");
        if(bookStoreService.save(dto)) {
            System.out.println("Details saved");
            model.addAttribute("successMessage", "Details saved Successfully");
            return getAllData(model);
        }else {
            model.addAttribute("message","Details not saved");
            model.addAttribute("dto",dto);
        }
        return "BookStore";
    }

    @GetMapping("/getAllData")
    public String getAllData(Model model)
    {
        System.out.println("getAllData method in controller");
        List<BookStoreDTO> list=bookStoreService.findAllEntity();
        list.forEach(System.out::println);
        model.addAttribute("listOfDto",list);
        return "ListOfEntity";
    }

    @GetMapping("/view")
    public String findById(@RequestParam("id")Integer id,Model model)
    {
        System.out.println("findById method in controller");
        BookStoreDTO dto=bookStoreService.findById(id);
        model.addAttribute("dto",dto);
        return "FindById";
    }
}
