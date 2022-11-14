package com.thanhsang.travelapp.model.Adds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schedule_ship")
public class ShipScheduleModel {
    
    @Id
    @Column(name = "id_schedule_ship", nullable = false, length = 255)
    private String id;
    @Column(name = "name_schedule_ship", nullable = false, length = 255)
    private String name;
    @Column(name = "address_pt", nullable = false, length = 255)
    private String addressPT;
    @Column(name = "address_pq", nullable = false, length = 255)
    private String addressPQ;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "pictures", nullable = false, length = 2048)
    private String pictures;
    @Column(name = "link_booking", nullable = false, length = 255)
    private String linkBooking;
    @Column(name = "phone_pt", nullable = false, length = 255)
    private String phonePT;
    @Column(name = "phone_pq", nullable = false, length = 255)
    private String phonePQ;
    @Column(name = "activity", nullable = false)
    private boolean activity;

    public ShipScheduleModel() {}

    public ShipScheduleModel(String id, String name, String addressPT, String addressPQ, String avatar, String pictures,
            String linkBooking, String phonePT, String phonePQ, boolean activity) {
        this.id = id;
        this.name = name;
        this.addressPT = addressPT;
        this.addressPQ = addressPQ;
        this.avatar = avatar;
        this.pictures = pictures;
        this.linkBooking = linkBooking;
        this.phonePT = phonePT;
        this.phonePQ = phonePQ;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddressPT(String addressPT) {
        this.addressPT = addressPT;
    }

    public void setAddressPQ(String addressPQ) {
        this.addressPQ = addressPQ;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public void setLinkBooking(String linkBooking) {
        this.linkBooking = linkBooking;
    }

    public void setPhonePT(String phonePT) {
        this.phonePT = phonePT;
    }

    public void setPhonePQ(String phonePQ) {
        this.phonePQ = phonePQ;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddressPT() {
        return addressPT;
    }

    public String getAddressPQ() {
        return addressPQ;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPictures() {
        return pictures;
    }

    public String getLinkBooking() {
        return linkBooking;
    }

    public String getPhonePT() {
        return phonePT;
    }

    public String getPhonePQ() {
        return phonePQ;
    }

    public boolean isActivity() {
        return activity;
    }

    
    
}
