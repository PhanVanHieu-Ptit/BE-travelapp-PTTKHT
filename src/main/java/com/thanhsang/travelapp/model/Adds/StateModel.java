package com.thanhsang.travelapp.model.Adds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class StateModel {
    
    @Id
    @Column(name = "id_state", nullable = false, length = 255)
    private String id;
    @Column(name = "name_state", nullable = false, length = 255)
    private String name;

    public StateModel() {}

    public StateModel(String name) {
        this.name = name;
    }

    public StateModel(String ID, String name) {
        this.id = ID;
        this.name = name;
    }

    public void setID(String ID) {
        this.id = ID;
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
