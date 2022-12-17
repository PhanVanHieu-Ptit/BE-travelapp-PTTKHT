package com.thanhsang.travelapp.repository.Adds;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.travelapp.model.Adds.AccountResponse;

@Repository
public interface AccountResponseRepo extends JpaRepository<AccountResponse, String>{
    
    @Query(value = "SELECT id_user id, 'Khách hàng' role, first_name, last_name, phone, sex, avatar "+
                   "FROM users WHERE id_user = ?1 "+
                   "UNION ALL " +
                   "SELECT id_membership id, (SELECT name_role FROM roles WHERE id_role = memberships.id_role) role, "+
                   "first_name, last_name, phone, sex, avatar FROM memberships WHERE id_membership = ?1", nativeQuery = true)
    public Optional<AccountResponse> findAccountById(String id);
}
