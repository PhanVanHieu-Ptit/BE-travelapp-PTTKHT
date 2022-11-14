package com.thanhsang.travelapp.repository.Adds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.ShipScheduleModel;

@Repository
public interface ShipScheduleRepo extends JpaRepository<ShipScheduleModel, Integer>{
    
}
