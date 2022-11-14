package com.thanhsang.travelapp.repository.Adds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.StateModel;

@Repository
public interface StateRepo extends JpaRepository<StateModel, Integer>{
    
}
