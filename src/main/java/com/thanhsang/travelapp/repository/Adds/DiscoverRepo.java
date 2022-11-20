package com.thanhsang.travelapp.repository.Adds;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.DiscoveryModel;

@Repository
public interface DiscoverRepo extends JpaRepository<DiscoveryModel, Integer>{

    Optional<DiscoveryModel> findById(String id);
    Page<DiscoveryModel> findAll(Pageable pageable);
    DiscoveryModel deleteById(String id);

}
