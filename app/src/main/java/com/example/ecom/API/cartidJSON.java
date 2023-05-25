package com.example.ecom.API;

import com.google.gson.annotations.SerializedName;

public class cartidJSON {
    @SerializedName("id")
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
