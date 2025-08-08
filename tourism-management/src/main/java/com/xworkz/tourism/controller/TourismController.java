package com.xworkz.tourism.controller;

import com.xworkz.tourism.dto.TourismDTO;
import com.xworkz.tourism.service.TourismService;
import com.xworkz.tourism.util.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TourismController {

    @Autowired
    private TourismService tourismService;

    public TourismController()
    {
        System.out.println("TourismController constructor");
    }

    @GetMapping("/redirectToTourism")
    public String getTourismForm()
    {
        System.out.println("returned tourism form page");
        return "Tourism";
    }

    @PostMapping("/tourism")
    public String getTourism(TourismDTO dto, Model model)
    {
        System.out.println("getTourism method");
        System.out.println("Controller data: "+dto);
        if(tourismService.validate(dto)) {
            model.addAttribute("message", "Submitted successfully");
        }else {
            model.addAttribute("message", "Invalid details");
            model.addAttribute("dto",dto);
        }
        DBConnection.closeResources();
        return "Tourism";
    }

}
