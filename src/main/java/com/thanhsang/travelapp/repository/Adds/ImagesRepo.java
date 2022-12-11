package com.thanhsang.travelapp.repository.Adds;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.ImagesModel;

@Repository
public interface ImagesRepo extends JpaRepository<ImagesModel, String> {
    
    public Optional<ImagesModel> findById(String id);

    
}
