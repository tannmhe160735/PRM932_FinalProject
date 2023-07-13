package com.example.prm392_finalproject;

import android.media.Image;

public class Gun_skin {
    private int id;
    private int Bundle;
    private String name;
    private int price;
    private String imageUrl;
    private int discount;

    public Gun_skin(){

    };

    public Gun_skin(int id, int bundle, String name, int price, String imageUrl) {
        this.id = id;
        Bundle = bundle;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Gun_skin(int id, int bundle, String name, int price, String imageUrl, int discount) {
        this.id = id;
        Bundle = bundle;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price - (price * discount/100);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
