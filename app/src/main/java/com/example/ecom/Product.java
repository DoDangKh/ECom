package com.example.ecom;

import java.util.jar.Attributes;

public class Product {
    private  int id;
    private int Resourceid;

    public Product() {
    }

    public int getResourceid() {
        return Resourceid;
    }

    public void setResourceid(int resourceid) {
        Resourceid = resourceid;
    }

    public int getId() {
        return id;
    }

    public Product(int id, int resourceid, String name, String description, int price) {
        this.id = id;
        Resourceid = resourceid;
        Name = name;
        Description = description;
        Price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String Name;
    private String Description;
    private int Price;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
