package com.thanhsang.travelapp.model.Login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "memberships")
public class MembershipModel {
 
    @Id
    @Column(name = "id_membership", nullable = true, length = 255)
    private String id;
    @Column(name = "id_role", nullable = true, length = 255)
    private String role;
    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;
    @Column(name = "link_facebook", nullable = false, length = 255)
    private String linkFacebook;
    @Column(name = "sex", nullable = false, length = 4)
    private String sex;
    @Column(name = "activity", nullable = false)
    private boolean activity;

    public MembershipModel() {
        this.id = "";
        this.role = "";
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.avatar = "";
        this.phone = "";
        this.linkFacebook = "";
        this.sex = "";
        this.activity = true;
    }

    public MembershipModel(String id, String role, String username, String password, String firstName, String lastName,
            String avatar, String phone, String linkFacebook, String sex, boolean activity) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.phone = phone;
        this.linkFacebook = linkFacebook;
        this.sex = sex;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPhone() {
        return phone;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public String getSex() {
        return sex;
    }

    public boolean getActivity() {
        return activity;
    }

    public boolean checkValid() {
        if(this.getId() == null || this.getId().equals("") ||
            this.getRole() == null || this.getRole().equals("") ||
            this.getUsername() == null || this.getUsername().equals("") ||
            this.getPassword() == null || this.getPassword().equals("") ||
            this.getFirstName() == null || this.getFirstName().equals("") ||
            this.getLastName() == null || this.getLastName().equals("") ||
            this.getAvatar() == null || this.getAvatar().equals("") ||
            this.getPhone() == null || this.getPhone().equals("") ||
            this.getSex() == null || this.getSex().equals("")) {
                
            return false;
        }

        return true;
    }

    public void changeRole(String role) {
        this.setRole(role);
    }

    public void changePassword(String password) {
        this.setPassword(password);
    }

    public void changeValid(MembershipModel membership) {
        this.setFirstName(membership.getFirstName());
        this.setLastName(membership.getLastName());
        this.setAvatar(membership.getAvatar());
        this.setPhone(membership.getPhone());
        this.setSex(membership.getSex());
    }

    public boolean compareMembership() {

        if(this.getId().equals("")) return true;

        return false;
    }

    public void changeActivity() {
        this.setActivity(!this.getActivity());
    }
}
    