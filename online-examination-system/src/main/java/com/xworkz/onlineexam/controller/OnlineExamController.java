package com.xworkz.onlineexam.controller;

import com.xworkz.onlineexam.dto.OnlineExamDTO;
import com.xworkz.onlineexam.service.OnlineExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class OnlineExamController {

    @Autowired
    private OnlineExamService onlineExamService;

    public OnlineExamController()
    {
        System.out.println("OnlineExamController constructor");
    }

    @GetMapping("/redirectToTourism")
    public String getOnlineExam()
    {
        System.out.println("redirected to online exam page");
        return "OnlineExam";
    }

    @PostMapping("/onlineExam")
    public String getExamForm(@Valid OnlineExamDTO dto, BindingResult bindingResult, Model model)
    {
        System.out.println("getExamForm method");
        System.out.println("controller data: "+dto);
        if(bindingResult.hasErrors())
        {
            System.out.println("Errors in fields");
            bindingResult.getFieldErrors().stream()
                    .map(e->e.getField()+" : "+e.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("message","Invalid details");
            return "OnlineExam";
        }
        System.out.println("All details are valid");
        if(onlineExamService.save(dto))
        {
            System.out.println("Details are saved");
            model.addAttribute("successMessage","Details saved");
        }else {
            System.out.println("Details not saved");
            model.addAttribute("message","Details not saved");
        }
        return "OnlineExam";
    }

}
