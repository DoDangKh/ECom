package com.example.ecom;

import com.example.ecom.API.ListProductJSON;
import com.example.ecom.API.ProductJSON;
import com.example.ecom.API.cartidJSON;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/product/get-all")
    Call<ListProductJSON> executeGetAllProduct();

    @GET("/product/{id}")
    Call<ListProductJSON> executeGetProduct(@Path("id") int id);

    @POST("/cart/add")
    Call<cartidJSON> executeaddtocart(@Body HashMap<String, String> map);

    @GET("/cart/get/{id}")
    Call<ListProductJSON> executeGetCartProduct(@Path("id") int id);
}
