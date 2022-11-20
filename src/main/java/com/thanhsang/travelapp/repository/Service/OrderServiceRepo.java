package com.thanhsang.travelapp.repository.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.RatingReponse;
import com.thanhsang.travelapp.model.Service.OrderServiceModel;

@Repository
public interface OrderServiceRepo extends JpaRepository<OrderServiceModel, Integer> {

    Optional<OrderServiceModel> findById(int id);
    Page<OrderServiceModel> findAllByIdUserAndIdState(String idUser, String idState, Pageable pageable);
    Page<OrderServiceModel> findAllByIdServiceAndIdState(String idService, String idState, Pageable pageable);

    @Query(value = "SELECT * FROM OrderService WHERE idService = ?1 AND idState = ?2 AND comment <> ?3" , nativeQuery = true)
    List<OrderServiceModel> findAllByIdServiceAndIdStateAndNotComment(int idService, int idState, String comment);

    @Query(value = "SELECT id_user idUser, "+
    "(SELECT Concat(first_name,' ',last_name) FROM users u WHERE u.id_user = ods.id_user) as name, "+
    "(SELECT avatar FROM users u WHERE u.id_user = ods.id_user) as avatar, "+
    "star, comment, date_now"+
    "FROM order_service ods WHERE ods.id_order_service = ?1 and ods.star = ?2", nativeQuery = true)
    List<Map<String, RatingReponse>> selectRatingByIdServiceAndStar(String idService, int star, Pageable pageable);

    @Query(value = "SELECT id_user idUser, "+
    "(SELECT Concat(first_name,' ',last_name) FROM users u WHERE u.id_user = ods.id_user) as name, "+
    "(SELECT avatar FROM users u WHERE u.id_user = ods.id_user) as avatar, "+
    "star, comment, date_now"+
    "FROM order_service ods WHERE ods.id_order_service = ?1 and ods.star = ?2", nativeQuery = true)
    List<Map<String, RatingReponse>> selectRatingByIdServiceAndStar2(String idService, int star);

    @Query(value = "SELECT COUNT(id_user) FROM order_service WHERE id_service = ?1 and star = ?2", nativeQuery = true)
    Integer selectNumerRatingByIdServiceAndStar(String idService, int star);

    @Query(value = "SELECT ((SELECT number FROM services s "+
    "WHERE s.id_service = ?1)-COUNT(id_order_service)) as number "+
    "FROM order_service os WHERE os.id_schedule_service = ?2 "+
    "AND os.date_start = ?3", nativeQuery = true)
    Integer findQuantityInStock(String idService, String idSchedule, Date dateStart);

}
