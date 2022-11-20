package com.thanhsang.travelapp.repository.Hotel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.OrderHistoryModel;
import com.thanhsang.travelapp.model.Adds.RatingReponse;
import com.thanhsang.travelapp.model.Hotel.OrderRoomModel;

@Repository
public interface OrderRoomRepo extends JpaRepository<OrderRoomModel, Integer> {
    
    Optional<OrderRoomModel> findById(int id);
    
    @Query(value = "SELECT odr.id_order_room, "+
    "odr.id_hotel, (SELECT name_hotel FROM hotels h WHERE h.id_hotel = odr.id_hotel) as name_hotel, "+
    "(SELECT avatar FROM hotels h WHERE h.id_hotel = odr.id_hotel), "+
    "odr.date_now FROM order_room odr WHERE odr.id_user = ?1 and odr.id_state = ?2", nativeQuery = true)
    List<Map<Integer, OrderHistoryModel>> findAllByIdUserAndIdState(String idUser, String idState, Pageable pageable);

    @Query(value = "SELECT odr.id_order_room, "+
    "odr.id_hotel, (SELECT name_hotel FROM hotels WHERE id_hotel = odr.id_hotel) as name_hotel, "+
    "odr.id_state, (SELECT name_state FROM states WHERE id_state = odr.id_state) as name_state, "+
    "odr.date_now FROM order_room odr WHERE odr.id_hotel = ?1 and odr.id_state = ?2", nativeQuery = true)
    List<Map<Integer, OrderHistoryModel>> findAllByIdHotelAndIdState(String idHotel, String idState, Pageable pageable);

    @Query(value = "SELECT COUNT(id_order_room) FROM order_room WHERE id_user = ?1 and id_state = ?2", nativeQuery = true)
    Integer selectNumberOrderByIdUserAndIdState(String idUser, String idState);

    @Query(value = "SELECT COUNT(id_order_room) FROM order_room WHERE id_hotel = ?1 and id_state = ?2", nativeQuery = true)
    Integer selectNumberOrderByIdHotelAndIdState(String idHotel, String idState);

    @Query(value = "SELECT ((SELECT r.number FROM rooms r WHERE r.id_room = ord.id_room) - sum(number)) as number "+
    "FROM order_room_detail ord WHERE id_order_room IN ( " +
    "SELECT id_order_room FROM order_room odr " +
    "WHERE id_hotel = ?1 and " +
    "((date_start <= ?2 and date_end > ?2) or " +
    "(date_start < ?3 and date_end >= ?3) or " +
    "(date_start >= ?2 and date_end <= ?3))) " +
    "and id_room = ?4 GROUP BY id_room", nativeQuery = true)
    Integer findQuantityInStock(String idHotel, Date date_start, Date date_end, String idRoom);

    @Query(value = "SELECT id_user idUser, "+
    "(SELECT Concat(first_name,' ',last_name) FROM users u WHERE u.id_user = odr.id_user) as name, "+
    "(SELECT avatar FROM users u WHERE u.id_user = odr.id_user) as avatar, "+
    "star, comment, date_end date "+
    "FROM order_room odr WHERE odr.id_hotel = ?1 and odr.star = ?2", nativeQuery = true)
    List<Map<String, RatingReponse>> selectRatingByIdHotelAndStar(String idHotel, int star, Pageable pageable);

    @Query(value = "SELECT id_user idUser, "+
    "(SELECT Concat(first_name,' ',last_name) FROM users u WHERE u.id_user = odr.id_user) as name, "+
    "(SELECT avatar FROM users u WHERE u.id_user = odr.id_user) as avatar, "+
    "star, comment, date_end date "+
    "FROM order_room odr WHERE odr.id_hotel = ?1", nativeQuery = true)
    List<Map<String, RatingReponse>> selectRatingByIdHotel(String idHotel, Pageable pageable);

    @Query(value = "SELECT COUNT(id_order) FROM order_room WHERE id_hotel = ?1 and star = ?2", nativeQuery = true)
    Integer selectNumberRatingByIdHotelAndStar(String idHotel, int star);

}
