package com.thanhsang.travelapp.model.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "services")
public class ServiceModel {
    
    @Id
    @Column(name = "id_service", nullable = false, length = 255)
    private String id;
    @Column(name = "id_type_service", nullable = false, length = 255)
    private String idTypeService;
    @Column(name = "id_membership", nullable = false, length = 255)
    private String idMembership;
    @Column(name = "name_service", nullable = false, length = 255)
    private String name;
    @Column(name = "unit", nullable = false, length = 255)
    private String unit;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "pictures", nullable = false, length = 2048)
    private String pictures;
    @Column(name = "description", nullable = false, length = 512)
    private String description;
    @Column(name = "star", nullable = false)
    private double star;
    @Column(name = "number_rating", nullable = false)
    private int numberRating;
    @Column(name = "activity", nullable = false)
    private boolean activity;

    public ServiceModel() {}

    public ServiceModel(String id, String idTypeService, String idMembership, String name, String unit, int price,
            int number, String phone, String avatar, String pictures, String description, double star, int numberRating, boolean activity) {
        this.id = id;
        this.idTypeService = idTypeService;
        this.idMembership = idMembership;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.number = number;
        this.phone = phone;
        this.avatar = avatar;
        this.pictures = pictures;
        this.description = description;
        this.star = star;
        this.numberRating = numberRating;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdTypeService(String idTypeService) {
        this.idTypeService = idTypeService;
    }

    public void setIdMembership(String idMembership) {
        this.idMembership = idMembership;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setStar(double star) {
        this.star = star;
    }

    public void setNumberRating(int numberRating) {
        this.numberRating = numberRating;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public String getIdTypeService() {
        return idTypeService;
    }

    public String getIdMembership() {
        return idMembership;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getPhone() {
        return phone;
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

    public double getStar() {
        return star;
    }

    public int getNumberRating() {
        return numberRating;
    }

    public boolean isActivity() {
        return activity;
    }

    public boolean checkValid() {
        if(this.idTypeService.equals("") ||
            this.name.equals("") ||
            this.price < 10000 ||
            this.number < 1) return false;
        return true;
    }
    
    public void changeValid(ServiceModel service) {
        this.setIdTypeService(service.getIdTypeService());
        this.setName(service.getName());
        this.setUnit(service.getUnit());
        this.setPrice(service.getPrice());
        this.setNumber(service.getNumber());
        this.setPhone(service.getPhone());
        this.setAvatar(service.getAvatar());
        this.setPictures(service.getPictures());
        this.setDescription(service.getDescription());
    }

    public void changeActivity() {
        this.setActivity(!this.activity);
    }

}