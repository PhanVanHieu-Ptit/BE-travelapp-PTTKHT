package com.thanhsang.travelapp.repository.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Food.OrderFoodModel;

@Repository
public interface OrderFoodRepo extends JpaRepository<OrderFoodModel, Integer>{
    
}
