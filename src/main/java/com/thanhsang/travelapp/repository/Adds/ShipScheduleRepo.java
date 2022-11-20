package com.thanhsang.travelapp.repository.Adds;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.ShipScheduleModel;

@Repository
public interface ShipScheduleRepo extends JpaRepository<ShipScheduleModel, Integer>{
    
    Optional<ShipScheduleModel> findById(String id);
    List<ShipScheduleModel> findAll();
    ShipScheduleModel deleteById(String id);

}
