package com.thanhsang.travelapp.model.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_hotel")
public class TypeHotelModel {
    
    @Id
    @Column(name = "id_type_hotel", nullable = false, length = 255)
    private String id;
    @Column(name = "name_type_hotel", nullable = false, length = 255)
    private String name;

    public TypeHotelModel() {}

    public TypeHotelModel(String iD, String name) {
        id = iD;
        this.name = name;
    }

    public TypeHotelModel(String name) {
        this.name = name;
    }

    public void setID(String iD) {
        id = iD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

}
