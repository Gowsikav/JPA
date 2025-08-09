package com.xworkz.foodorder.repository;

import com.xworkz.foodorder.entity.FoodOrderEntity;

public interface FoodOrderRepository {

    boolean save(FoodOrderEntity foodOrderEntity);
}
