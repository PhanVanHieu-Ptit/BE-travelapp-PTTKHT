package com.thanhsang.travelapp.model.Food;

import java.io.Serializable;

public class OrderFoodDetailKey implements Serializable{
    
    private int idOrder;
    private int idDish;

    public OrderFoodDetailKey() {}

    public OrderFoodDetailKey(int idOrder, int idDish) {
        this.idOrder = idOrder;
        this.idDish = idDish;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdDish() {
        return idDish;
    }

}
