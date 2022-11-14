package com.thanhsang.travelapp.model.Food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "order_food_detail")
@IdClass(OrderFoodDetailKey.class)
public class OrderFoodDetailModel {
    
    @Id
    @Column(name = "id_order_food", nullable = false)
    private int idOrder;
    @Id
    @Column(name = "id_dish", nullable = false, length = 255)
    private String idDish;
    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "price", nullable = false)
    private int price;

    public OrderFoodDetailModel() {}

    public OrderFoodDetailModel(int idOrder, String idDish, int number, int price) {
        this.idOrder = idOrder;
        this.idDish = idDish;
        this.number = number;
        this.price = price;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdDish(String idDish) {
        this.idDish = idDish;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public String getIdDish() {
        return idDish;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    
}
