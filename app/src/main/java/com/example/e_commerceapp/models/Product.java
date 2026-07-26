package com.example.e_commerceapp.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String price;
    private int image;
    private float rating;

    public Product() {
    }

    public Product(int id, String name, String price, int image, float rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}