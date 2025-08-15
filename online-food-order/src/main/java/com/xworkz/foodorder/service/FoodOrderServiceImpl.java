package com.xworkz.foodorder.service;

import com.xworkz.foodorder.dto.FoodOrderDTO;
import com.xworkz.foodorder.entity.FoodOrderEntity;
import com.xworkz.foodorder.repository.FoodOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public FoodOrderDTO findById(Integer id) {
        System.out.println("findById method in service");
        FoodOrderEntity entity=foodOrderRepository.findById(id);
        FoodOrderDTO dto=new FoodOrderDTO();
        dto.setFoodId(entity.getFoodId());
        dto.setFoodName(entity.getFoodName());
        dto.setRestaurantName(entity.getRestaurantName());
        dto.setQuantity(entity.getQuantity());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    @Override
    public String updateFoodOrderById(FoodOrderDTO dto) {
        System.out.println("updateFoodOrderById method in service");
        FoodOrderEntity entity=new FoodOrderEntity();
        BeanUtils.copyProperties(dto,entity);
        if(foodOrderRepository.updateFoodOrderById(entity))
            return "Data updated";
        return "Data not updated";
    }

    @Override
    public String deleteById(Integer id) {
        System.out.println("deleteById method in service");
        if(foodOrderRepository.deleteById(id))
            return "Data deleted";
        return "Data not deleted";
    }

    @Override
    public List<FoodOrderDTO> searchByFoodName(String name) {
        System.out.println("searchByFoodName method in service");
       List<FoodOrderEntity> entities=foodOrderRepository.searchByFoodName(name);
       List<FoodOrderDTO> list=new ArrayList<>();
       for(FoodOrderEntity entity:entities) {
           FoodOrderDTO dto = new FoodOrderDTO();
           BeanUtils.copyProperties(entity, dto);
           list.add(dto);
       }
        return list;
    }
}
