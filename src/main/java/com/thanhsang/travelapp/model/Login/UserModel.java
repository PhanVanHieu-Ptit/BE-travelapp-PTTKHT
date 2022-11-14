package com.thanhsang.travelapp.model.Login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
    
    @Id
    @Column(name = "id_user", nullable = false, length = 255)
    private String id;
    @Column(name = "id_social", nullable = false, length = 255)
    private String idSocial;
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;
    @Column(name = "sex", nullable = false, length = 4)
    private String sex;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "platform", nullable = false, length = 8)
    private String platform;
    
    public UserModel() {
        this.id = "";
        this.idSocial = "";
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.sex = "";
        this.avatar = "";
        this.platform = "";
    }

    public UserModel(String id, String idSocial, String firstName, String lastName, String phone, String sex,
            String avatar, String platform) {
        this.id = id;
        this.idSocial = idSocial;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.sex = sex;
        this.avatar = avatar;
        this.platform = platform;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdSocial(String idSocial) {
        this.idSocial = idSocial;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public String getIdSocial() {
        return idSocial;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPlatform() {
        return platform;
    }

    public boolean checkValid() {
        if(this.id.equals("") || this.id == null ||
        this.idSocial.equals("") || this.idSocial == null ||
        // this.firstName.equals("") || this.firstName == null ||
        // this.lastName.equals("") || this.lastName == null ||
        // this.phone.equals("") || this.phone == null ||
        // this.sex.equals("") || this.sex == null ||
        // this.avatar.equals("") || this.avatar == null ||
        this.platform.equals("") || this.platform == null) return false;
        
        return true;
    }

    public void update(UserModel user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.sex = user.getSex();
        this.avatar = user.getAvatar();
    }
   
}
