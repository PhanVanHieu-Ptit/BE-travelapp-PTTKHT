package com.thanhsang.travelapp.repository.Food;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Food.FoodModel;

@Repository
public interface FoodRepo extends JpaRepository<FoodModel, Integer>{
    
    List<FoodModel> findAll();
    Optional<FoodModel> findById(String id);
    Page<FoodModel> findAllByIdTypeFood(String type, Pageable pageable);
    Page<FoodModel> findAllByActivity(boolean activity, Pageable pageable);
    Page<FoodModel> findAllByActivityAndIdTypeFood(boolean activity, String type, Pageable pageable);
    List<FoodModel> findTop10ByOrderByStarDesc();

}
