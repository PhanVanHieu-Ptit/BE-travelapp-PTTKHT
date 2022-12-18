package com.thanhsang.travelapp.repository.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Service.ServiceModel;

@Repository
public interface ServiceRepo extends JpaRepository<ServiceModel, Integer>{
    
    Optional<ServiceModel> findById(String idService);

    Optional<ServiceModel> findByIdMembership(String idMembership);
    
    Page<ServiceModel> findAll(Pageable pageable);

    Page<ServiceModel> findAllByActivityAndIdTypeService(boolean activity, String idTypeService, Pageable pageable) throws Exception;

    Page<ServiceModel> findAllByActivity(boolean activity, Pageable pageable) throws Exception;

    Page<ServiceModel> findAllByIdTypeService(String type, Pageable pageable) throws Exception;

    List<ServiceModel> findTop10ByOrderByStarDesc();
    
    
    // @Query("select * from ("
    // +"   SELECT services.id_type_service as type_service,count(services.id_type_service) as number_services"
    // +"   FROM services GROUP BY services.id_type_service) as aTable "
    // +"   order by aTable.number_services desc"
    // +"   limit 3" )
    // List<ServiceModel> findTop3Services();

    // @Transactional
    // @Modifying
    // @Query(name = "UPDATE Service SET name = ?2, unit ?3, " +
    //                 "price = ?4, phone = ?5, numService = ?6, " +
    //                 "picture = ?7, images = ?8, description = ?9, " +
    //                 "activity = ?10 WHERE ID = ?1", nativeQuery = true)
    // void updateService(int id, String name, String unit, int price, String phone, int numService, 
    //                     String picture, String images, String description, boolean activity);
}

