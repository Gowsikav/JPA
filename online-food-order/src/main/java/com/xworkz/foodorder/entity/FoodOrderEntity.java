package com.xworkz.foodorder.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "food_order_info")
@NamedQuery(name = "findAllEntity",query = "select a from FoodOrderEntity a")
@NamedQuery(name = "findById",query = "select a from FoodOrderEntity a where a.foodId=:id")
@NamedQuery(name = "updateFoodOrderById",query = "update FoodOrderEntity a set a.foodName=:foodName,a.quantity=:quantity," +
        "a.restaurantName=:restaurantName,a.description=:description,a.price=:price where a.foodId=:foodId")
@NamedQuery(name = "deleteById",query = "delete from FoodOrderEntity a where a.foodId=:foodId")
@NamedQuery(name = "searchByFoodName",query = "select a from FoodOrderEntity a where a.foodName=:foodName")
public class FoodOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;
}
