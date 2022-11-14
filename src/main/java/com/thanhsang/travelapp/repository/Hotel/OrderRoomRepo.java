package com.thanhsang.travelapp.repository.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Hotel.OrderRoomModel;

@Repository
public interface OrderRoomRepo extends JpaRepository<OrderRoomModel, Integer>{
    
}
