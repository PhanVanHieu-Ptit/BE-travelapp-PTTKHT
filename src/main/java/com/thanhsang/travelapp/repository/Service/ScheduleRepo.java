package com.thanhsang.travelapp.repository.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

import com.thanhsang.travelapp.model.Service.ScheduleModel;

@Repository
public interface ScheduleRepo extends JpaRepository<ScheduleModel, Integer>{
    
    List<ScheduleModel> findAll();
    List<ScheduleModel> findAllByIdService(String idService);
    Optional<ScheduleModel> findById(String id);
    List<ScheduleModel> findAllByIdServiceAndActivity(String idService, boolean activity);

    // @Transactional
    // @Modifying
    // @Query(name = "UPDATE Schedule SET name = ?2 WHERE ID = ?1 AND idService = ?3", nativeQuery = true)
    // void updateSchedule(int id, String name, int idService);

    // @Query(name = "SELECT EXISTS (SELECT ID FROM Schedule WHERE ID = ?1 AND idService = ?2)")
    // Boolean existsByIdScheduleAndIdService(int idSchedule, int idService);
}
