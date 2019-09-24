package com.hvktmm.at13.model;

import com.jfoenix.controls.JFXButton;

import java.util.Date;

public class User {
    private  int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private int is_staff;
    private String email;
    private String phone_number;
    private String address;
    private Date date_of_birth;
    private String gender;
    private JFXButton edit;

    public User(String first_name, String last_name, String username, String password, String email, String phone_number, String address, Date date_of_birth, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
    }
    public User(String first_name, String last_name, String username, String email, String phone_number, String address, Date date_of_birth, String gender, JFXButton edit) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.edit = edit;

    }
    public User() {
    }

    public JFXButton getEdit() {
        return edit;
    }

    public void setEdit(JFXButton edit) {
        this.edit = edit;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(int is_staff) {
        this.is_staff = is_staff;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
