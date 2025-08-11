package com.xworkz.foodorder.service;

import com.xworkz.foodorder.dto.FoodOrderDTO;
import com.xworkz.foodorder.entity.FoodOrderEntity;
import com.xworkz.foodorder.repository.FoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodOrderServiceImpl implements FoodOrderService{

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    public FoodOrderServiceImpl()
    {
        System.out.println("FoodOrderServiceImpl constructor");
    }

    @Override
    public boolean save(FoodOrderDTO foodOrderDTO) {
        System.out.println("save method in service");
        System.out.println("service data: "+foodOrderDTO);

        FoodOrderEntity entity=new FoodOrderEntity();
        entity.setFoodName(foodOrderDTO.getFoodName());
        entity.setPrice(foodOrderDTO.getPrice());
        entity.setDescription(foodOrderDTO.getDescription());
        entity.setQuantity(foodOrderDTO.getQuantity());
        entity.setRestaurantName(foodOrderDTO.getRestaurantName());

        return foodOrderRepository.save(entity);
    }

    @Override
    public List<FoodOrderDTO> findAllDto() {
        System.out.println("find all Dto in service");
        List<FoodOrderDTO> orderDTOList=null;
        List<FoodOrderEntity> orderEntities=foodOrderRepository.findAllEntity();
        orderDTOList=orderEntities.stream().map(entity->{
            FoodOrderDTO foodOrderDTO=new FoodOrderDTO();
            foodOrderDTO.setFoodId(entity.getFoodId());
            foodOrderDTO.setFoodName(entity.getFoodName());
            foodOrderDTO.setDescription(entity.getDescription());
            foodOrderDTO.setQuantity(entity.getQuantity());
            foodOrderDTO.setPrice(entity.getPrice());
            foodOrderDTO.setRestaurantName(entity.getRestaurantName());
            return foodOrderDTO;
        }).collect(Collectors.toList());
        return orderDTOList;
    }
}
