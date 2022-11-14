package com.thanhsang.travelapp.model.Login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleModel {
    
    @Id
    @Column(name = "id_role", nullable = false, length = 255)
    private String id;
    @Column(name = "name_role", nullable = false, length = 255)
    private String name;

    public RoleModel() {}

    public RoleModel(String iD, String name) {
        id = iD;
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
