package com.example.e_commerceapp.models;

public class Address {

    private int id;
    private String label;
    private String fullName;
    private String streetAddress;
    private String city;
    private String phoneNumber;
    private boolean isDefault;

    public Address(int id, String label, String fullName, String streetAddress,
                   String city, String phoneNumber, boolean isDefault) {
        this.id = id;
        this.label = label;
        this.fullName = fullName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}