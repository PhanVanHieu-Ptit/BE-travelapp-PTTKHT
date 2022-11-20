package com.thanhsang.travelapp.model.Hotel;

import java.io.Serializable;

public class OrderRoomDetailKey implements Serializable{
    
    private int idOrder;
    private String idRoom;

    public OrderRoomDetailKey() {}

    public OrderRoomDetailKey(int idOrder, String idRoom) {
        this.idOrder = idOrder;
        this.idRoom = idRoom;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public String getIdRoom() {
        return idRoom;
    }

}
