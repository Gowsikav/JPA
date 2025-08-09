package com.xworkz.foodorder.controller;

import com.xworkz.foodorder.dto.FoodOrderDTO;
import com.xworkz.foodorder.service.FoodOrderService;
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
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    public FoodOrderController()
    {
        System.out.println("FoodOrderController constructor");
    }

    @GetMapping("/redirectToTourism")
    public String getFoodOrderPage()
    {
        System.out.println("redirected to food order page");
        return "FoodOrder";
    }

    @PostMapping("/foodOrder")
    public String getOrder(@Valid FoodOrderDTO dto, BindingResult bindingResult, Model model)
    {
        System.out.println("getOrder method in controller");
        System.out.println("controller data: "+dto);
        if(bindingResult.hasErrors())
        {
            bindingResult.getFieldErrors().stream()
                    .map(e->e.getField()+" : "+e.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("dto",dto);
            model.addAttribute("message","Invalid details");
            return "FoodOrder";
        }
        System.out.println("All details are valid");
        if(foodOrderService.save(dto)) {
            System.out.println("Details saved");
            model.addAttribute("successMessage", "Details saved");
        }else {
            System.out.println("Details not saved");
            model.addAttribute("message","Details not saved");
        }
        return "FoodOrder";
    }
}
