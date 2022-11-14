package com.thanhsang.travelapp.model.Adds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discovery")
public class DiscoveryModel {
    
    @Id
    @Column(name = "id_discovery", nullable = false, length = 255)
    private String id;
    @Column(name = "name_discovery", nullable = false, length = 255)
    private String name;
    @Column(name = "address", nullable = false, length = 255)
    private String address;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "pictures", nullable = false, length = 2048)
    private String pictures;
    @Column(name = "description", nullable = false, length = 512)
    private String description;
    @Column(name = "longitude", nullable = false)
    private Double longiture;
    @Column(name = "latitude", nullable = false)
    private Double latitude;
    @Column(name = "cost", nullable = false, length = 255)
    private String cost;
    @Column(name = "activity", nullable = false)
    private boolean activity;

    public DiscoveryModel() {}

    public DiscoveryModel(String id, String name, String address, String avatar, String pictures, String description,
            Double longiture, Double latitude, String cost, boolean activity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.pictures = pictures;
        this.description = description;
        this.longiture = longiture;
        this.latitude = latitude;
        this.cost = cost;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongiture(Double longiture) {
        this.longiture = longiture;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public String getAddress() {
        return address;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPictures() {
        return pictures;
    }

    public String getDescription() {
        return description;
    }

    public Double getLongiture() {
        return longiture;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getCost() {
        return cost;
    }

    public boolean isActivity() {
        return activity;
    }

   
}
