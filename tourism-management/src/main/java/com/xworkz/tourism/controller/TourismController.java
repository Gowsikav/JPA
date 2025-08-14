package com.xworkz.tourism.controller;

import com.xworkz.tourism.dto.TourismDTO;
import com.xworkz.tourism.service.TourismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class TourismController {

    @Autowired
    private TourismService tourismService;

    public TourismController() {
        System.out.println("TourismController constructor");
    }

    @GetMapping("/redirectToTourism")
    public String getTourismForm() {
        System.out.println("returned tourism form page");
        return "Tourism";
    }

    @GetMapping("/getIndex")
    public String getIndex() {
        System.out.println("returned index page");
        return "index";
    }

    @PostMapping("/tourism")
    public String getTourism(TourismDTO dto, Model model) {
        System.out.println("getTourism method");
        System.out.println("Controller data: " + dto);
        if (tourismService.validate(dto)) {
            model.addAttribute("message", "Submitted successfully");
            return getAllEntity(model);
        } else {
            model.addAttribute("message", "Invalid details");
            model.addAttribute("dto", dto);
        }
        return "Tourism";
    }

    @GetMapping("/getAllEntity")
    public String getAllEntity(Model model) {
        System.out.println("getAllEntity method in controller");
        List<TourismDTO> list = tourismService.getAllEntity();
        list.forEach(System.out::println);
        model.addAttribute("listOfDto", list);
        return "ListOfTourism";
    }

    @GetMapping("/view")
    public String getById(@RequestParam String id,Model model) {
        System.out.println("getById method in controller");
        System.out.println("Id: " + id);
        Optional<TourismDTO> optionalTourismDTO=tourismService.findById(Integer.parseInt(id));
        if(optionalTourismDTO.isPresent())
        {
            System.out.println("Data found");
            System.out.println(optionalTourismDTO.get());
            model.addAttribute("ref",optionalTourismDTO.get());
        }else {
            System.out.println("Data not found");
            model.addAttribute("errorMessage", "No package found for ID: " + id);
        }
        return "DisplayId";
    }

    @GetMapping("/edit")
    public String getUpdateById(@RequestParam("id")Integer id,Model model)
    {
        System.out.println("getUpdateById method in service");
        System.out.println("Id: " + id);
        Optional<TourismDTO> optionalTourismDTO=tourismService.findById(id);
        if(optionalTourismDTO.isPresent())
        {
            System.out.println("Data found");
            System.out.println(optionalTourismDTO.get());
            model.addAttribute("dto",optionalTourismDTO.get());
        }
        return "UpdateById";
    }

    @PostMapping("updateById")
    public String updateTourismDto(TourismDTO tourismDTO,Model model)
    {
        System.out.println("updateTourismDto method in controller");
        String msg=tourismService.updateTourismEntityById(tourismDTO);
        System.out.println(msg);
        List<TourismDTO> list = tourismService.getAllEntity();
        model.addAttribute("message",msg);
        model.addAttribute("listOfDto", list);
        return "ListOfTourism";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") Integer id,Model model)
    {
        System.out.println("deleteById method in controller");
        String check= tourismService.deleteTourismById(id);
        System.out.println("Delete:" +check);
        List<TourismDTO> list = tourismService.getAllEntity();
        model.addAttribute("message","Data deleted");
        model.addAttribute("listOfDto", list);
        return "ListOfTourism";
    }

}
