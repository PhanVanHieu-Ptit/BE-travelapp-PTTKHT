package com.thanhsang.travelapp.model.Food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dishs")
public class DishModel {
    
    @Id
    @Column(name = "id_dish", nullable = false, length = 255)
    private String id;
    @Column(name = "id_food", nullable = false, length = 255)
    private String idFood;
    @Column(name = "name_dish", nullable = false, length = 255)
    private String name;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "unit", nullable = false, length = 255)
    private String unit;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "activity", nullable = false)
    private boolean activity;
    

    public DishModel() {}


    public DishModel(String id, String idFood, String name, int price, String unit, String avatar, boolean activity) {
        this.id = id;
        this.idFood = idFood;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.avatar = avatar;
        this.activity = activity;
    }


    public void setId(String id) {
        this.id = id;
    }


    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public void setActivity(boolean activity) {
        this.activity = activity;
    }


    public String getId() {
        return id;
    }


    public String getIdFood() {
        return idFood;
    }


    public String getName() {
        return name;
    }


    public int getPrice() {
        return price;
    }


    public String getUnit() {
        return unit;
    }


    public String getAvatar() {
        return avatar;
    }


    public boolean isActivity() {
        return activity;
    }

    public boolean checkValid() {
        if(this.id.equals("") || 
            this.idFood.equals("") || 
            this.name.equals("") ||
            this.unit.equals("") ||
            this.avatar.equals("") ||
            this.price <= 1000) 
        return false;

        return true;
    }

    public void update(DishModel dish) {
        this.setName(dish.getName());
        this.setUnit(dish.getUnit());
        this.setPrice(dish.getPrice());
        this.setAvatar(dish.getAvatar());
        this.setActivity(dish.isActivity());
    }
 
}
