package com.thanhsang.travelapp.model.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class RoomModel {
    
    @Id
    @Column(name = "id_room", nullable = false, length = 255)
    private String id;
    @Column(name = "id_hotel", nullable = false, length = 255)
    private String idHotel;
    @Column(name = "name_room", nullable = false, length = 255)
    private String name;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "pictures", nullable = false, length = 2048)
    private String pictures;
    @Column(name = "description", nullable = false, length = 512)
    private String description;
    @Column(name = "area", nullable = false)
    private int area;
    @Column(name = "activity", nullable = false)
    private boolean activity;

    public RoomModel() {}

    public RoomModel(String id, String idHotel, String name, int price, int number, String pictures, String description,
            int area, boolean activity) {
        this.id = id;
        this.idHotel = idHotel;
        this.name = name;
        this.price = price;
        this.number = number;
        this.pictures = pictures;
        this.description = description;
        this.area = area;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getPictures() {
        return pictures;
    }

    public String getDescription() {
        return description;
    }

    public int getArea() {
        return area;
    }

    public boolean isActivity() {
        return activity;
    }

    public boolean checkValid() {
        if(this.name == null || this.name.equals("") ||
            this.area <= 1 ||
            this.price <= 100000 ||
            this.number <= 0 ||
            this.pictures == null || this.pictures.equals("") ||
            this.description == null || this.description.equals("")) 
            return false;
        return true;
    }

    public void changeValid(RoomModel room) {
        this.name = room.getName();
        this.area = room.getArea();
        this.price = room.getPrice();
        this.number = room.getNumber();
        this.pictures = room.getPictures();
        this.description = room.getDescription();
        this.activity = room.isActivity();
    }
}