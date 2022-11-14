package com.thanhsang.travelapp.repository.Food;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Food.DishModel;

@Repository
public interface DishRepo extends JpaRepository<DishModel, Integer>{
    
    Optional<DishModel> findById(String id);
    List<DishModel> findAllByIdFood(String idFood);
}
