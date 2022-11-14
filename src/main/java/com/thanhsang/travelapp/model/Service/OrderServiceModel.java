package com.thanhsang.travelapp.model.Service;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_service")
public class OrderServiceModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_order_service", nullable = false)
    private int id;
    @Column(name = "id_user", nullable = false, length = 255)
    private String idUser;
    @Column(name = "id_state", nullable = false, length = 255)
    private String idState;
    @Column(name = "id_schedule_service", nullable = false, length = 255)
    private String idSchedule;
    @Column(name = "date_now", nullable = false)
    private Timestamp dateNow;
    @Column(name = "date_start", nullable = false)
    private Date dateStart;
    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "phone_customer", nullable = false, length = 255)
    private String phone;
    @Column(name = "star", nullable = true)
    private int star;
    @Column(name = "comment", nullable = true, length = 255)
    private String comment;

    public OrderServiceModel() {}

    public OrderServiceModel(int id, String idUser, String idState, String idSchedule, Timestamp dateNow,
        Date dateStart, int number, int price, String phone, int star, String comment) {
        this.id = id;
        this.idUser = idUser;
        this.idState = idState;
        this.idSchedule = idSchedule;
        this.dateNow = dateNow;
        this.dateStart = dateStart;
        this.number = number;
        this.price = price;
        this.phone = phone;
        this.star = star;
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setIdState(String idState) {
        this.idState = idState;
    }

    public void setIdSchedule(String idSchedule) {
        this.idSchedule = idSchedule;
    }

    public void setDateNow(Timestamp dateNow) {
        this.dateNow = dateNow;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdState() {
        return idState;
    }

    public String getIdSchedule() {
        return idSchedule;
    }

    public Timestamp getDateNow() {
        return dateNow;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public int getStar() {
        return star;
    }

    public String getComment() {
        return comment;
    }

    
}