package com.thanhsang.travelapp.model.Adds;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountResponse {
    
    @Id
    private String id;
    private String role;
    private String first_name;
    private String last_name;
    private String phone;
    private String sex;
    private String avatar;

    public AccountResponse() {}
    
    public AccountResponse(String id, String role, String first_name, String last_name, String phone, String sex,
            String avatar) {
        this.id = id;
        this.role = role;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.sex = sex;
        this.avatar = avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public String getAvatar() {
        return avatar;
    }

    
}
