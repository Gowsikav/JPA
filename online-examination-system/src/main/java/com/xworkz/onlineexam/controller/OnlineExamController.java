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
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class OnlineExamController {

    @Autowired
    private OnlineExamService onlineExamService;

    public OnlineExamController() {
        System.out.println("OnlineExamController constructor");
    }

    @GetMapping("/redirectToTourism")
    public String getOnlineExam() {
        System.out.println("redirected to online exam page");
        return "OnlineExam";
    }

    @GetMapping("/getIndex")
    public String getIndex() {
        System.out.println("returned index page");
        return "index";
    }

    @PostMapping("/onlineExam")
    public String getExamForm(@Valid OnlineExamDTO dto, BindingResult bindingResult, Model model) {
        System.out.println("getExamForm method");
        System.out.println("controller data: " + dto);
        if (bindingResult.hasErrors()) {
            System.out.println("Errors in fields");
            bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + " : " + e.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("message", "Invalid details");
            return "OnlineExam";
        }
        System.out.println("All details are valid");
        if (onlineExamService.save(dto)) {
            System.out.println("Details are saved");
            model.addAttribute("successMessage", "Details saved");
            return getAllData(model);
        } else {
            System.out.println("Details not saved");
            model.addAttribute("message", "Details not saved");
        }
        return "OnlineExam";
    }

    @GetMapping("/findAllData")
    public String getAllData(Model model) {
        System.out.println("getAll data method in controller");
        List<OnlineExamDTO> list = onlineExamService.findAllEntity();
        list.forEach(System.out::println);
        model.addAttribute("listOfDto", list);
        return "ListOfData";
    }

    @GetMapping("/view")
    public String findById(@RequestParam("id") Integer id, Model model) {
        System.out.println("findById method in controller");
        OnlineExamDTO dto = onlineExamService.findById(id);
        model.addAttribute("dto", dto);
        return "FindById";
    }

    @GetMapping("/edit")
    public String editById(@RequestParam("id") Integer id, Model model) {
        System.out.println("editById method in controller");
        OnlineExamDTO dto = onlineExamService.findById(id);
        model.addAttribute("dto", dto);
        return "UpdateOnlineExam";
    }

    @PostMapping("/updateOnlineExam")
    public String updateOnlineExam(@Valid OnlineExamDTO examDTO, BindingResult bindingResult, Model model) {
        System.out.println("updateOnlineExam method in controller");
        if (bindingResult.hasErrors()) {
            System.out.println("Errors in fields");
            bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + " : " + e.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("message", "Invalid details");
            return "UpdateOnlineExam";
        }
        String updated = onlineExamService.updateOnlineExamById(examDTO);
        System.out.println("updated: " + updated);
        List<OnlineExamDTO> list = onlineExamService.findAllEntity();
        model.addAttribute("listOfDto", list);
        return "ListOfData";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") Integer id, Model model) {
        System.out.println("deleteById method in controller");
        String deleted = onlineExamService.deleteById(id);
        System.out.println("deleted " + deleted);
        List<OnlineExamDTO> list = onlineExamService.findAllEntity();
        model.addAttribute("listOfDto", list);
        return "ListOfData";
    }

    @GetMapping("/search")
    public String searchOnlineExamBySubject(@RequestParam("subject") String subject, Model model) {
        System.out.println("searchOnlineExamBySubject method in controller");
        List<OnlineExamDTO> list = onlineExamService.searchBySubject(subject);
        list.forEach(System.out::println);
        if (list.isEmpty()) {
            model.addAttribute("message", "Details not found");
        }
        model.addAttribute("listOfDto", list);
        return "ListOfData";
    }
}
