package com.thanhsang.travelapp.repository.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Service.OrderServiceModel;

@Repository
public interface OrderServiceRepo extends JpaRepository<OrderServiceModel, Integer> {

    Optional<OrderServiceModel> findById(int id);
    List<OrderServiceModel> findAllByIdUser(int idUser);

    @Query(value = "SELECT * FROM OrderService WHERE idService = ?1 AND idState = ?2 AND comment <> ?3" , nativeQuery = true)
    List<OrderServiceModel> findAllByIdServiceAndIdStateAndNotComment(int idService, int idState, String comment);
}
