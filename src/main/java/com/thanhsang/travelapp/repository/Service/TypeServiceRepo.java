package com.thanhsang.travelapp.repository.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Service.TypeServiceModel;


@Repository
public interface TypeServiceRepo extends JpaRepository<TypeServiceModel, String>{
    
    List<TypeServiceModel> findAll();
    Optional<TypeServiceModel> findById(String ID);

}
