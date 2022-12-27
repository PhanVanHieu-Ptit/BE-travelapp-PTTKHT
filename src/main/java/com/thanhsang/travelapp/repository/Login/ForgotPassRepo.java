package com.thanhsang.travelapp.repository.Login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.thanhsang.travelapp.model.Login.ForgotPassModel;

@Repository
public interface ForgotPassRepo extends JpaRepository<ForgotPassModel, String> {
    @Query(value = "SELECT * FROM forgot_password where hash_token= ?1", nativeQuery = true)
    public Optional<ForgotPassModel> findByToken(String token);
}
