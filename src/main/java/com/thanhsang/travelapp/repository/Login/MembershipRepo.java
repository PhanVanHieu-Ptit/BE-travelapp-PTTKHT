package com.thanhsang.travelapp.repository.Login;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Login.MembershipModel;

@Repository
public interface MembershipRepo extends JpaRepository<MembershipModel, String>{
    public List<MembershipModel> findAll();
    public Optional<MembershipModel> findById(String id);
    public Optional<MembershipModel> findByUsername(String username);
    @Query(value="SELECT * FROM memberships Where email= ?1",nativeQuery = true)
    public Optional<MembershipModel> findByEmail(String email);
    public Page<MembershipModel> findAllByRole(String id, Pageable pageable);
    
}
