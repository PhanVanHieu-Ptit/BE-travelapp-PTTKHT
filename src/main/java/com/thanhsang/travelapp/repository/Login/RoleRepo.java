package com.thanhsang.travelapp.repository.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Login.RoleModel;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, String>{
    
}
