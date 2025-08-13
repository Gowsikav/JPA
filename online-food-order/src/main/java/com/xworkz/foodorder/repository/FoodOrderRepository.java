package com.xworkz.foodorder.repository;

import com.xworkz.foodorder.entity.FoodOrderEntity;

import java.util.List;

public interface FoodOrderRepository {

    boolean save(FoodOrderEntity foodOrderEntity);
    List<FoodOrderEntity> findAllEntity();
    FoodOrderEntity findById(Integer id);
}

