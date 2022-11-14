package com.thanhsang.travelapp.repository.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Hotel.TypeHotelModel;

@Repository
public interface TypeHotelRepo extends JpaRepository<TypeHotelModel, String>{
    
}
