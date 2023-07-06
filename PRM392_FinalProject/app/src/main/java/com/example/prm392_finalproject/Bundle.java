package com.example.prm392_finalproject;

import android.widget.ImageView;
import android.widget.TextView;

public class Bundle {
    private int id;
    private String name;
    private int price;
    private int image;
    private String Date_start;
    private String Date_end;

    public Bundle() {
    }

    public Bundle(String name, int price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Bundle(int id, String name, int price, int image, String date_start, String date_end) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        Date_start = date_start;
        Date_end = date_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDate_start() {
        return Date_start;
    }

    public void setDate_start(String date_start) {
        Date_start = date_start;
    }

    public String getDate_end() {
        return Date_end;
    }

    public void setDate_end(String date_end) {
        Date_end = date_end;
    }
}
