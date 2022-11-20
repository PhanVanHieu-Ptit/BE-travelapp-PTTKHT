package com.thanhsang.travelapp.model.Food;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderFoodHistoryModel {
    @Id
    @Column(name = "id_order_food", nullable = false)
    Integer idOrder;
    @Column(name = "id_food", nullable = false)
    String idFood;
    @Column(name = "name_food", nullable = false)
    String nameFood;
    @Column(name = "id_state", nullable = false)
    String idState;
    @Column(name = "name_state", nullable = false)
    String nameState;
    @Column(name = "date_now", nullable = false)
    Timestamp dateNow;
    
    public OrderFoodHistoryModel() {}

    public OrderFoodHistoryModel(Integer idOrder, String idFood, String nameFood, String idState, String nameState,
            Timestamp dateNow) {
        this.idOrder = idOrder;
        this.idFood = idFood;
        this.nameFood = nameFood;
        this.idState = idState;
        this.nameState = nameState;
        this.dateNow = dateNow;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public void setIdState(String idState) {
        this.idState = idState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    public void setDateNow(Timestamp dateNow) {
        this.dateNow = dateNow;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public String getIdFood() {
        return idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public String getIdState() {
        return idState;
    }

    public String getNameState() {
        return nameState;
    }

    public Timestamp getDateNow() {
        return dateNow;
    }
    
}
