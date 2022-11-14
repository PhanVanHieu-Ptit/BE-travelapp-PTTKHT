package com.thanhsang.travelapp.model.Food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_food")
public class TypeFoodModel {
    
    @Id
    @Column(name = "id_type_food", nullable = false, length = 255)
    private String id;
    @Column(name = "name_type_food", nullable = false, length = 255)
    private String name;

    public TypeFoodModel() {}

    public TypeFoodModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeFoodModel(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
