package com.example.e_commerceapp.models;

public class Category {

    private int id;
    private String name;
    private int image;
    private Class activityClass;

    public Category() {
    }

    public Category(
            int id,
            String name,
            int image,
            Class activityClass
    ) {

        this.id = id;
        this.name = name;
        this.image = image;
        this.activityClass = activityClass;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }
}