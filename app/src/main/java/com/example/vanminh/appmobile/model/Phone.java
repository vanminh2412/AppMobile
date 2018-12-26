package com.example.vanminh.appmobile.model;

import java.io.Serializable;

public class Phone implements Serializable {
    private int id;
    private String name;
    private float price;
    private String pro_id;

    public Phone() {
    }

    public Phone(int id, String name, float price, String pro_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pro_id = pro_id;
    }

    public Phone(String name, float price, String pro_id) {
        this.name = name;
        this.price = price;
        this.pro_id = pro_id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }
}
