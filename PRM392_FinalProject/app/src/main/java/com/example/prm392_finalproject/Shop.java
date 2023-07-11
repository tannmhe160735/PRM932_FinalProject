package com.example.prm392_finalproject;

import java.util.Date;

public class Shop {
    private int id;
    private int gun1;
    private int gun2;
    private int gun3;
    private int gun4;
    private Date Date_start;
    private Date Date_end;

    public Shop() {
    }
    public Shop(int id, int gun1, int gun2, int gun3, int gun4, Date date_start, Date date_end) {
        this.id = id;
        this.gun1 = gun1;
        this.gun2 = gun2;
        this.gun3 = gun3;
        this.gun4 = gun4;
        Date_start = date_start;
        Date_end = date_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGun1() {
        return gun1;
    }

    public void setGun1(int gun1) {
        this.gun1 = gun1;
    }

    public int getGun2() {
        return gun2;
    }

    public void setGun2(int gun2) {
        this.gun2 = gun2;
    }

    public int getGun3() {
        return gun3;
    }

    public void setGun3(int gun3) {
        this.gun3 = gun3;
    }

    public int getGun4() {
        return gun4;
    }

    public void setGun4(int gun4) {
        this.gun4 = gun4;
    }

    public Date getDate_start() {
        return Date_start;
    }

    public void setDate_start(Date date_start) {
        Date_start = date_start;
    }

    public Date getDate_end() {
        return Date_end;
    }

    public void setDate_end(Date date_end) {
        Date_end = date_end;
    }
}
