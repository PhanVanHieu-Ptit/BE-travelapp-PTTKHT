package com.thanhsang.travelapp.repository.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Food.TypeFoodModel;

@Repository
public interface TypeFoodRepo extends JpaRepository<TypeFoodModel, String>{
    
}
