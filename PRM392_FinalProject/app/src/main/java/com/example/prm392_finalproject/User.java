package com.example.prm392_finalproject;

public class User {
    private int User_id;
    private String User_name;
    private String Password;
    private int Role;

    public User(){

    }

    public User(int User_id, String User_name, String Password, int Role) {
        this.User_id = User_id;
        this.User_name = User_name;
        this.Password = Password;
        this.Role = Role;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }
}
