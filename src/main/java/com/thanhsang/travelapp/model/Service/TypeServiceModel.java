package com.thanhsang.travelapp.model.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_service")
public class TypeServiceModel {
    
    @Id
    @Column(name = "id_type_service", nullable = false, length = 255)
    private String id;
    @Column(name = "name_type_service", nullable = false, length = 255)
    private String name;

    public TypeServiceModel() {}

    public TypeServiceModel(String id, String name) {
        this.id = id;
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
