package com.thanhsang.travelapp.model.Adds;

import java.util.Date;

public class RatingReponse {
    
    private String idUser;
    private String name;
    private String avatar;
    private int star;
    private String comment;
    private Date date;

    public RatingReponse() {}
    
    public RatingReponse(String idUser, String name, String avatar, int star, String comment, Date date) {
        this.idUser = idUser;
        this.name = name;
        this.avatar = avatar;
        this.star = star;
        this.comment = comment;
        this.date = date;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getStar() {
        return star;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    
}
