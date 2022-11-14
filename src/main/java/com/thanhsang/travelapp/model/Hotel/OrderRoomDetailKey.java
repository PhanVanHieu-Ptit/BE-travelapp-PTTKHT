package com.thanhsang.travelapp.model.Hotel;

import java.io.Serializable;

public class OrderRoomDetailKey implements Serializable{
    
    private int idOrder;
    private int idRoom;

    public OrderRoomDetailKey() {}

    public OrderRoomDetailKey(int idOrder, int idRoom) {
        this.idOrder = idOrder;
        this.idRoom = idRoom;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdRoom() {
        return idRoom;
    }

}
