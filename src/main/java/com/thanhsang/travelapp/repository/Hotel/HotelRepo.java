package com.thanhsang.travelapp.repository.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Hotel.HotelModel;

@Repository
public interface HotelRepo extends JpaRepository<HotelModel, Integer>{
    
    List<HotelModel> findAll();
    Optional<HotelModel> findById(String id);
    Page<HotelModel> findAllByIdTypeHotel(String type, Pageable pageable);
    Page<HotelModel> findAllByActivity(boolean activity, Pageable pageable);
    Page<HotelModel> findAllByActivityAndIdTypeHotel(boolean activity, String type, Pageable pageable);
    List<HotelModel> findTop10ByOrderByStarDesc();

}
