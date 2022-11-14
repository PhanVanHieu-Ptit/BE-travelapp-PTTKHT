package com.thanhsang.travelapp.repository.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Food.OrderFoodDetailKey;
import com.thanhsang.travelapp.model.Food.OrderFoodDetailModel;

@Repository
public interface OrderFoodDetailRepo extends JpaRepository<OrderFoodDetailModel, OrderFoodDetailKey>{
    
}
