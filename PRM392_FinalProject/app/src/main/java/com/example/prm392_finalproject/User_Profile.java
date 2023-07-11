package com.example.prm392_finalproject;

public class User_Profile {
    private int User_id;
    private String Server;
    private int Level ;
    private int Exp ;
    private int Valorant_Point ;
    private int Radianite_Point ;
    private int Free_Agent ;
    public User_Profile(){
    };


    public User_Profile(int user_id, String Server, int level , int exp, int radianite_Point, int valorant_Point, int free_Agent) {
        User_id = user_id;
        this.Server = Server;
        this.Level =level;
        this.Exp = exp;
        this.Valorant_Point =valorant_Point;
        this.Radianite_Point =radianite_Point;
        this.Free_Agent =free_Agent;

    }

    public int getUser_id() {
        return User_id;
    }

    public String getServer() {
        return Server;
    }

    public int getLevel() {
        return Level;
    }

    public int getExp() {
        return Exp;
    }

    public int getValorant_Point() {
        return Valorant_Point;
    }

    public int getRadianite_Point() {
        return Radianite_Point;
    }

    public int getFree_Agent() {
        return Free_Agent;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public void setServer(String server) {
        Server = server;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public void setExp(int exp) {
        Exp = exp;
    }

    public void setValorant_Point(int valorant_Point) {
        Valorant_Point = valorant_Point;
    }

    public void setRadianite_Point(int radianite_Point) {
        Radianite_Point = radianite_Point;
    }

    public void setFree_Agent(int free_Agent) {
        Free_Agent = free_Agent;
    }
};

