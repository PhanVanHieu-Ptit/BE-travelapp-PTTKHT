package com.thanhsang.travelapp.model.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "order_room_detail")
@IdClass(OrderRoomDetailKey.class)
public class OrderRoomDetailModel {
    
    @Id
    @Column(name = "id_order_room", nullable = false)
    private int idOrder;
    @Id
    @Column(name = "id_room", nullable = false, length = 255)
    private String idRoom;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "number", nullable = false)
    private int number;

    public OrderRoomDetailModel() {}

    public OrderRoomDetailModel(int idOrder, String idRoom, int price, int number) {
        this.idOrder = idOrder;
        this.idRoom = idRoom;
        this.price = price;
        this.number = number;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
    
}
