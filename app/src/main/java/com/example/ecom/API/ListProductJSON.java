package com.example.ecom.API;

import com.example.ecom.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListProductJSON {
    @SerializedName("data")
    @Expose
    private ArrayList<Product> listProduct;

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
