package com.thanhsang.travelapp.model.Hotel;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_room")
public class OrderRoomModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_order_room", nullable = false)
    private int id;
    @Column(name = "id_user", nullable = false, length = 255)
    private String idUser;
    @Column(name = "id_state", nullable = false, length = 255)
    private String idState;
    @Column(name = "date_start", nullable = false)
    private Date dateStart;
    @Column(name = "date_end", nullable = false)
    private Date dateEnd;
    @Column(name = "date_now", nullable = false)
    private Timestamp dateNow;
    @Column(name = "phone_customer", nullable = false, length = 255)
    private String phone;
    @Column(name = "star", nullable = true)
    private int star;
    @Column(name = "comment", nullable = true, length = 255)
    private String comment;

    public OrderRoomModel() {}

    public OrderRoomModel(int id, String idUser, String idState, Date dateStart, Date dateEnd,
            Timestamp dateNow, String phone, int star, String comment) {
        this.id = id;
        this.idUser = idUser;
        this.idState = idState;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateNow = dateNow;
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

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateNow(Timestamp dateNow) {
        this.dateNow = dateNow;
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

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Timestamp getDateNow() {
        return dateNow;
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