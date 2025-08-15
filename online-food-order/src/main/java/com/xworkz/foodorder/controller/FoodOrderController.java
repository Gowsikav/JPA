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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/getIndex")
    public String getIndex()
    {
        System.out.println("returned index  page");
        return "index";
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
            return getAllData(model);
        }else {
            System.out.println("Details not saved");
            model.addAttribute("message","Details not saved");
        }
        return "FoodOrder";
    }

    @GetMapping("/findAll")
    public  String getAllData(Model model)
    {
        System.out.println("get all Data method in controller");
        List<FoodOrderDTO> list=foodOrderService.findAllDto();
        list.forEach(System.out::println);
        model.addAttribute("listOfDto",list);
        return "ListOfData";
    }

    @GetMapping("/view")
    public String getById(@RequestParam("id") Integer id, Model model)
    {
        System.out.println("getById method in controller");
        FoodOrderDTO dto=foodOrderService.findById(id);
        model.addAttribute("dto",dto);
        return "FindById";
    }

    @GetMapping("/edit")
    public String editById(@RequestParam("id") Integer id,Model model)
    {
        System.out.println("editById method in controller");
        FoodOrderDTO dto=foodOrderService.findById(id);
        model.addAttribute("dto",dto);
        return "UpdateFoodOrder";
    }

    @PostMapping("/updateFoodOrder")
    public String updateFoodOrder(@Valid FoodOrderDTO foodOrderDTO,BindingResult bindingResult,Model model)
    {
        System.out.println("updateFoodOrder method in controller");
        if(bindingResult.hasErrors())
        {
            bindingResult.getFieldErrors().stream()
                    .map(e->e.getField()+" : "+e.getDefaultMessage())
                    .forEach(System.out::println);
            model.addAttribute("dto",foodOrderDTO);
            model.addAttribute("message","Invalid details");
            return "UpdateFoodOrder";
        }
        String updated=foodOrderService.updateFoodOrderById(foodOrderDTO);
        System.out.println("Updated: "+updated);
        List<FoodOrderDTO> list=foodOrderService.findAllDto();
        model.addAttribute("listOfDto",list);
        return "ListOfData";
    }

    @GetMapping("/delete")
    public String deleteTourismById(@RequestParam("id") Integer id, Model model)
    {
        System.out.println("deleteTourismById method in controller");
        String deleted=foodOrderService.deleteById(id);
        System.out.println("Deleted: "+deleted);
        List<FoodOrderDTO> list=foodOrderService.findAllDto();
        model.addAttribute("listOfDto",list);
        return "ListOfData";
    }

    @GetMapping("/search")
    public String searchByFoodName(@RequestParam("foodName") String name,Model model)
    {
        System.out.println("searchByFoodName method in controller");
        List<FoodOrderDTO> foodOrderDTO=foodOrderService.searchByFoodName(name);
        foodOrderDTO.forEach(System.out::println);
        if(foodOrderDTO.isEmpty()) {
            model.addAttribute("message", "Details not found");
        }
        model.addAttribute("listOfDto",foodOrderDTO);
        return "ListOfData";
    }
}
