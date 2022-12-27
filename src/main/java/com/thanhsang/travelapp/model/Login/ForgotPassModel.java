package com.thanhsang.travelapp.model.Login;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "forgot_password")
public class ForgotPassModel {
    @Id
    @Column(name = "id_user", nullable = true, length = 255)
    private String id_user;
    @Column(name = "time_expired")
    private Timestamp time_expired;
    @Column(name = "hash_token",length = 255)
    private String hash_token;
    @Column(name = "used")
    private boolean used;
    public ForgotPassModel() {
    }
    public ForgotPassModel(String id_user, Timestamp time_expired, String hash_token, boolean used) {
        this.id_user = id_user;
        this.time_expired = time_expired;
        this.hash_token = hash_token;
        this.used = used;
    }
    public String getId_user() {
        return id_user;
    }
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
    public Timestamp getTime_expired() {
        return time_expired;
    }
    public void setTime_expired(Timestamp time_expired) {
        this.time_expired = time_expired;
    }
    public String getHash_token() {
        return hash_token;
    }
    public void setHash_token(String hash_token) {
        this.hash_token = hash_token;
    }
    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
    public boolean isValid(){
        // chưa dùng và còn hạn
        return !used && new Timestamp(System.currentTimeMillis()).compareTo(this.time_expired)<= 0 ? true: false ;
    }
    
}
