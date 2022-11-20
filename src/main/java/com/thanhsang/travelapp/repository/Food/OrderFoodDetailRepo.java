package com.thanhsang.travelapp.repository.Food;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.OrderDetailReponse;
import com.thanhsang.travelapp.model.Food.OrderFoodDetailKey;
import com.thanhsang.travelapp.model.Food.OrderFoodDetailModel;

@Repository
public interface OrderFoodDetailRepo extends JpaRepository<OrderFoodDetailModel, OrderFoodDetailKey>{
    
    boolean deleteAllByIdOrder(int idOrder);

    @Query(value = "SELECT id_dish, (SELECT name_dish FROM dishs r WHERE r.id_dish = ord.id_dish), "+
                    "price, number FROM order_food_detail ord WHERE id_order_food = ?1", nativeQuery = true)
    List<Map<String, OrderDetailReponse>> findAllByIdOrder(int idOrder);

}
