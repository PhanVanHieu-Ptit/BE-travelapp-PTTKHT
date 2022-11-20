package com.thanhsang.travelapp.model.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotels")
public class HotelModel {
    
    @Id
    @Column(name = "id_hotel", nullable = false, length = 255)
    private String id;
    @Column(name = "id_membership", nullable = false, unique = true, length = 255)
    private String idMembership;
    @Column(name = "id_type_hotel", nullable = false, length = 255)
    private String idTypeHotel;
    @Column(name = "name_hotel", nullable = false, length = 255)
    private String name;
    @Column(name = "address_hotel", nullable = false, length = 255)
    private String address;
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Column(name = "description", nullable = false, length = 512)
    private String description;
    @Column(name = "star", nullable = false)
    private double star;
    @Column(name = "price_min", nullable = false)
    private int priceMin;
    @Column(name = "price_max", nullable = false)
    private int priceMax;
    @Column(name = "number_rating", nullable = false)
    private int numberRating;
    @Column(name = "activity", nullable = false)
    private boolean activity;
    
    public HotelModel() {
        this.idMembership = "";
        this.idTypeHotel = "";
        this.name = "";
        this.address = "";
        this.phone = "";
        this.avatar = "";
        this.description = "";
    }

    public HotelModel(String id, String idMembership, String idTypeHotel, String name, String address, String phone,
            String avatar, double longitude, double latitude, String description, double star, int priceMin, int priceMax, boolean bancol,
            boolean motobike, boolean clean, boolean wifi, boolean laudry, boolean shuttle, boolean activity, int numberRating) {
        this.id = id;
        this.idMembership = idMembership;
        this.idTypeHotel = idTypeHotel;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.star = star;
        this.numberRating = numberRating;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdMembership(String idMembership) {
        this.idMembership = idMembership;
    }

    public void setIdTypeHotel(String idTypeHotel) {
        this.idTypeHotel = idTypeHotel;
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

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public String getIdMembership() {
        return idMembership;
    }

    public String getIdTypeHotel() {
        return idTypeHotel;
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

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
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

    public int getPriceMin() {
        return priceMin;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public boolean isActivity() {
        return activity;
    }

    public boolean checkValid() {
        if(
            this.getId().equals("") || this.getId() == null ||
            this.getIdMembership().equals("") || this.getIdMembership() == null ||
            this.getIdTypeHotel().equals("") || this.getIdTypeHotel() == null ||
            this.getName().equals("") || this.getName() == null ||
            this.getAddress().equals("") || this.getAddress() == null ||
            this.getPhone().equals("") || this.getPhone() == null ||
            this.getAvatar().equals("") || this.getAvatar() == null ||
            this.getDescription().equals("") || this.getDescription() == null) 
            return false;
            return true;
    }

    public void changeValid(HotelModel hotel) {
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.phone = hotel.getPhone();
        this.avatar = hotel.getAvatar();
        this.description = hotel.getDescription();
        this.longitude = hotel.getLongitude();
        this.latitude = hotel.getLatitude();
        this.priceMin = hotel.getPriceMin();
        this.priceMax = hotel.getPriceMax();
    }
}