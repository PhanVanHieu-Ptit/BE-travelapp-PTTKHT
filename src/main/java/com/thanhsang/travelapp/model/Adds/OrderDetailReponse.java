package com.thanhsang.travelapp.model.Adds;

public class OrderDetailReponse {
    private String idRoom;
    private String nameRoom;
    private int price;
    private int number;

    OrderDetailReponse() {}

    public OrderDetailReponse(String idRoom, String nameRoom, int price, int number) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.price = price;
        this.number = number;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    
}
