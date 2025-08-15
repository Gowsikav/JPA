package com.xworkz.foodorder.service;

import com.xworkz.foodorder.dto.FoodOrderDTO;

import java.util.List;

public interface FoodOrderService {

    boolean save(FoodOrderDTO foodOrderDTO);
    List<FoodOrderDTO> findAllDto();
    FoodOrderDTO findById(Integer id);
    String updateFoodOrderById(FoodOrderDTO dto);
    String deleteById(Integer id);
    List<FoodOrderDTO> searchByFoodName(String name);
}
