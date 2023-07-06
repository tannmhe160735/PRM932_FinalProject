package com.example.prm392_finalproject;

import android.media.Image;

public class Gun_skin {
    private int id;
    private int Bundle;
    private String name;
    private int price;
    private int imageId;

    public Gun_skin(int id, int bundle, String name, int price, int imageId) {
        this.id = id;
        Bundle = bundle;
        this.name = name;
        this.price = price;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBundle() {
        return Bundle;
    }

    public void setBundle(int bundle) {
        Bundle = bundle;
    }
}
