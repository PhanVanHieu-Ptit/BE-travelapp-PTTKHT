package com.thanhsang.travelapp.model.Food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foods")
public class FoodModel {
    
    @Id
    @Column(name = "id_food", nullable = false, length = 255)
    private String id;
    @Column(name = "id_type_food", nullable = false, length = 255)
    private String idTypeFood;    
    @Column(name = "id_membership", nullable = false, unique = true, length = 255)
    private String idMembership;
    @Column(name = "name_food", nullable = false, length = 255)
    private String name;
    @Column(name = "address_food", nullable = false, length = 255)
    private String address;
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "description", nullable = false, length = 512)
    private String description;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Column(name = "star", nullable = false)
    private double star;
    @Column(name = "number_rating", nullable = false)
    private int numberRating;
    @Column(name = "activity", nullable = false)
    private boolean activity;
    
    public FoodModel() {}

    public FoodModel(String id, String idTypeFood, String idMembership, String name, String address, String phone,
            String avatar, String description, double longiture, double latitude, double star, boolean activity, int numberRating) {
        this.id = id;
        this.idTypeFood = idTypeFood;
        this.idMembership = idMembership;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.description = description;
        this.longitude = longiture;
        this.latitude = latitude;
        this.star = star;
        this.numberRating = numberRating;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdTypeFood(String idTypeFood) {
        this.idTypeFood = idTypeFood;
    }

    public void setIdMembership(String idMembership) {
        this.idMembership = idMembership;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setStar(double star) {
        this.star = star;
    }

    

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public String getIdTypeFood() {
        return idTypeFood;
    }

    public String getIdMembership() {
        return idMembership;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDescription() {
        return description;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getStar() {
        return star;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setNumberRating(int numberRating) {
        this.numberRating = numberRating;
    }

    public int getNumberRating() {
        return numberRating;
    }

    public boolean checkValid() {
        if(
            this.getId().equals("") || this.getId() == null ||
            this.getIdMembership().equals("") || this.getIdMembership() == null ||
            this.getStar() >= 0 ||
            this.getName().equals("") || this.getName() == null ||
            this.getAddress().equals("") || this.getAddress() == null ||
            this.getPhone().equals("") || this.getPhone() == null ||
            this.getAvatar().equals("") || this.getAvatar() == null ||
            this.getDescription().equals("") || this.getDescription() == null) 
            return false;
            return true;
    }

    public void changeValid(FoodModel food) {
        this.name = food.getName();
        this.address = food.getAddress();
        this.phone = food.getPhone();
        this.avatar = food.getAvatar();
        this.description = food.getDescription();
        this.longitude = food.getLongitude();
        this.latitude = food.getLatitude();
    }
    
}