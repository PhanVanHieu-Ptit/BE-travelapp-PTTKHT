package com.thanhsang.travelapp.repository.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Hotel.RoomModel;

@Repository
public interface RoomRepo extends JpaRepository<RoomModel, Integer>{
    
    Optional<RoomModel> findById(String idRoom);
    List<RoomModel> findAllByIdHotel(String idHotel);
    List<RoomModel> findAllByIdHotelAndActivity(String idHotel, boolean activity);

}
