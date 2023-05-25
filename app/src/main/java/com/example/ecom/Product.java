package com.example.ecom;

import com.google.gson.annotations.SerializedName;

import java.util.jar.Attributes;

public class Product {
    @SerializedName("id")
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

    @SerializedName("Name")
    private String Name;
    @SerializedName("Description")
    private String Description;
    @SerializedName("price")
    private int Price;

    @SerializedName("amount")
    private int amount;

    @SerializedName("soluong")
    private  int soluong;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

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
