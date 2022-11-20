package com.thanhsang.travelapp.repository.Food;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.OrderHistoryModel;
import com.thanhsang.travelapp.model.Adds.RatingReponse;
import com.thanhsang.travelapp.model.Food.OrderFoodModel;

@Repository
public interface OrderFoodRepo extends JpaRepository<OrderFoodModel, Integer>{
    
    @Query(value = "SELECT odf.id_order_food, "+
    "odf.id_food, (SELECT name_food FROM foods WHERE id_food = odf.id_food) as name_food, "+
    "(SELECT avatar FROM foods WHERE id_food = odf.id_food),"+
    "odf.date_now FROM order_food odf WHERE odf.id_user = ?1 and odf.id_state = ?2 ORDER BY ?#{#pageable}", nativeQuery = true)
    List<Map<Integer, OrderHistoryModel>> findAllByIdUserAndIdState(String idUser, String idState, Pageable pageable);

    @Query(value = "SELECT odf.id_order_food, "+
    "odf.id_food, (SELECT name_food FROM foods WHERE id_food = odf.id_food) as name_food, "+
    "(SELECT avatar FROM foods WHERE id_food = odf.id_food),"+
    "odf.date_now FROM order_food odf WHERE odf.id_food = ?1 and odf.id_state = ?2 ORDER BY ?#{#pageable}", nativeQuery = true)
    List<Map<Integer, OrderHistoryModel>> findAllByIdFoodAndIdState(String idFood, String idState, Pageable pageable);

    @Query(value = "SELECT COUNT(id_order_food) FROM order_food WHERE id_user = ?1 and id_state = ?2", nativeQuery = true)
    Integer selectNumberOrderByIdUserAndIdState(String idUser, String idState);

    @Query(value = "SELECT COUNT(id_order_food) FROM order_food WHERE id_food = ?1 and id_state = ?2", nativeQuery = true)
    Integer selectNumberOrderByIdFoodAndIdState(String idFood, String idState);

    @Query(value = "SELECT id_user idUser, "+
    "(SELECT Concat(first_name,' ',last_name) FROM users u WHERE u.id_user = odf.id_user) as name, "+
    "(SELECT avatar FROM users u WHERE u.id_user = odf.id_user) as avatar, "+
    "star, comment, date_end date "+
    "FROM order_food odf WHERE odf.id_food = ?1 and odf.star = ?2", nativeQuery = true)
    List<Map<String, RatingReponse>> selectRatingByIdFoodAndStar(String idFood, int star, Pageable pageable);


    @Query(value = "SELECT COUNT(id_order) FROM order_room WHERE id_food = ?1 and star = ?2", nativeQuery = true)
    Integer selectNumberRatingByIdFoodAndStar(String idFood, int star);

    
}
