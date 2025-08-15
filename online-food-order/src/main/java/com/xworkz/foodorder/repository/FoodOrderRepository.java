package com.xworkz.foodorder.repository;

import com.xworkz.foodorder.entity.FoodOrderEntity;

import java.util.List;

public interface FoodOrderRepository {

    boolean save(FoodOrderEntity foodOrderEntity);
    List<FoodOrderEntity> findAllEntity();
    FoodOrderEntity findById(Integer id);
    Boolean updateFoodOrderById(FoodOrderEntity entity);
    Boolean deleteById(Integer id);
    List<FoodOrderEntity> searchByFoodName(String name);

}

