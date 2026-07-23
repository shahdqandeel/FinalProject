package com.example.e_commerceapp.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String price;
    private int image;
    private float rating;
    private String reviewsCount;
    private String description;
    private String category;
    private String[] colors;
    private String[] sizes;
    private String[] storages;

    public Product(){}

    public Product(int id, String name, String price, int image, float rating,String reviewsCount,
                   String description,String category,String[] colors,String[] sizes,String[] storages){
        this.id=id;
        this.name=name;
        this.price=price;
        this.image=image;
        this.rating=rating;
        this.reviewsCount=reviewsCount;
        this.description=description;
        this.category=category;
        this.colors=colors;
        this.sizes=sizes;
        this.storages=storages;
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

    public String getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(String reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String[] getSizes() {
        return sizes;
    }

    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }

    public String[] getStorages() {
        return storages;
    }

    public void setStorages(String[] storages) {
        this.storages = storages;
    }
}
