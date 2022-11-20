package com.thanhsang.travelapp.model.Adds;

import java.sql.Timestamp;

public class OrderHistoryModel {

    Integer idOrder;
    String idHotel;
    String avatar;
    String nameState;
    Timestamp dateNow;
    
    public OrderHistoryModel() {}

    public OrderHistoryModel(Integer idOrder, String idHotel, String avatar, String nameState, Timestamp dateNow) {
        this.idOrder = idOrder;
        this.idHotel = idHotel;
        this.avatar = avatar;
        this.nameState = nameState;
        this.dateNow = dateNow;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    public void setDateNow(Timestamp dateNow) {
        this.dateNow = dateNow;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNameState() {
        return nameState;
    }

    public Timestamp getDateNow() {
        return dateNow;
    }
    
    
}
