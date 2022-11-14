package com.thanhsang.travelapp.repository.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Hotel.OrderRoomDetailKey;
import com.thanhsang.travelapp.model.Hotel.OrderRoomDetailModel;

@Repository
public interface OrderRoomDetailRepo extends JpaRepository<OrderRoomDetailModel, OrderRoomDetailKey>{
    
}
