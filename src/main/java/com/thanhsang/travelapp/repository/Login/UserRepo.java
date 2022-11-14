package com.thanhsang.travelapp.repository.Login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Login.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer>{
    
    public Optional<UserModel> findById(String id);
    public Optional<UserModel> findByIdSocial(String idSocial);
}
