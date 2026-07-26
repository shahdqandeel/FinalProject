package com.example.e_commerceapp.models;

public class Accessories {

    private int id;
    private String name;
    private int rating;
    private float price;
    private int image;

    public Accessories() {
    }

    public Accessories(
            int id,
            String name,
            int rating,
            float price,
            int image
    ) {

        this.id = id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.image = image;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}