package com.example.prm392_finalproject;

import android.net.Uri;

import java.util.Date;

public class Bundle {
    private int id;
    private String name;
    private int price;
    private String image;
    private Date Date_start;
    private Date Date_end;
    private String Bundle_Youtube_Uri;

    public Bundle() {
    }

    public Bundle(int id, String name, int price, String image, Date date_start, Date date_end) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        Date_start = date_start;
        Date_end = date_end;
    }

    public Bundle(int id, String name, int price, String image, Date date_start, Date date_end, String bundle_Youtube_Uri) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        Date_start = date_start;
        Date_end = date_end;
        Bundle_Youtube_Uri = bundle_Youtube_Uri;
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


    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
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

    public String getBundle_Youtube_Uri() {
        return Bundle_Youtube_Uri;
    }

    public void setBundle_Youtube_Uri(String bundle_Youtube_Uri) {
        Bundle_Youtube_Uri = bundle_Youtube_Uri;
    }
}
