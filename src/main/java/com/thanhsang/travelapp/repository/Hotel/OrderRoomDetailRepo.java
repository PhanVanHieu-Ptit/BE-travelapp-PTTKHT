package com.thanhsang.travelapp.repository.Hotel;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.OrderDetailReponse;
import com.thanhsang.travelapp.model.Hotel.OrderRoomDetailKey;
import com.thanhsang.travelapp.model.Hotel.OrderRoomDetailModel;

@Repository
public interface OrderRoomDetailRepo extends JpaRepository<OrderRoomDetailModel, OrderRoomDetailKey>{
    // List<OrderRoomDetailModel> findAllByIdOrder(int idOrder);
    boolean deleteAllByIdOrder(int idOrder);

    @Query(value = "SELECT id_room, (SELECT name_room FROM rooms r WHERE r.id_room = ord.id_room), "+
                    "price, number FROM order_room_detail ord WHERE id_order_room = ?1", nativeQuery = true)
    List<Map<String, OrderDetailReponse>> findAllByIdOrder(int idOrder);

}
