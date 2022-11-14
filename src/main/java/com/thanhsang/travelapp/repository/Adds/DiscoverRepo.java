package com.thanhsang.travelapp.repository.Adds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.DiscoveryModel;

@Repository
public interface DiscoverRepo extends JpaRepository<DiscoveryModel, Integer>{

}
