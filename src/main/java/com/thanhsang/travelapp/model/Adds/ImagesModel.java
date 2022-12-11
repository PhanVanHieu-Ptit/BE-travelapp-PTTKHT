package com.thanhsang.travelapp.model.Adds;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.thanhsang.travelapp.util.generateUUID;

@Entity
@Table(name = "images")
public class ImagesModel implements Serializable {
    
    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Lob
    @Column(name = "data", nullable = false)
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    public ImagesModel() {}

    public ImagesModel(String id, String name, byte[] data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public void insertImage(String name, byte[] data) {
        this.id = generateUUID.generateUuid();
        this.name = name;
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }


    
}
