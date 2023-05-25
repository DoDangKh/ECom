package com.example.ecom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecom.API.ListProductJSON;
import com.example.ecom.API.ProductJSON;
import com.example.ecom.API.cartidJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetail extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Photoadapter photoadapter;
    private TextView Name;
    private TextView Description;
    private TextView price;
    private DBhelper db=new DBhelper(this);
    private Button btbuynow;
    private  int productid=0;
    private Button btGoToCart;
    private Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();

        setContentView(R.layout.activity_product_detail);

//        Intent intent=getIntent();
        btbuynow=findViewById(R.id.buynowbttn);
        btGoToCart=findViewById(R.id.gotocartbttn);
        String vl=getIntent().getStringExtra("vl");
        Log.d("CREATION",vl);
//        Cursor data=db.GetData("Select * From Products where id ="+vl);
        apiService=retrofit.create(ApiService.class);
        Call<ListProductJSON> call=apiService.executeGetProduct(Integer.valueOf(vl));

        call.enqueue(new Callback<ListProductJSON>() {
            @Override
            public void onResponse(Call<ListProductJSON> call, Response<ListProductJSON> response) {
                ListProductJSON rs=response.body();
                anhxa();
                productid=rs.getListProduct().get(0).getId();
                Log.d("CREATIONPRODUCT",rs.getListProduct().get(0).getName());
                price.setText(Integer.toString(rs.getListProduct().get(0).getPrice())+"USD");
                Name.setText(rs.getListProduct().get(0).getName());
                Description.setText(rs.getListProduct().get(0).getDescription());
                photoadapter= new Photoadapter(ProductDetail.this, getListPhoto(rs.getListProduct().get(0)));
                viewPager.setAdapter(photoadapter);

                circleIndicator.setViewPager(viewPager);
                photoadapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
            }

            @Override
            public void onFailure(Call<ListProductJSON> call, Throwable t) {
                Log.d("CREATIONPRODUCT",t.getMessage());
            }
        });

//        Product p=new Product();
//        while(data.moveToNext()){
//            p.setId(data.getInt(0));
//            p.setName(data.getString(1));
//            p.setDescription(data.getString(2));
//            p.setPrice(data.getInt(3));
//        }

//        Log.d("Creation",Integer.toString(p.getPrice()));




        btbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map=new HashMap<>();
                map.put("id",String.valueOf(1));
                map.put("productid",String.valueOf(productid));
                Call<cartidJSON> call=apiService.executeaddtocart(map);
                call.enqueue(new Callback<cartidJSON>() {
                    @Override
                    public void onResponse(Call<cartidJSON> call, Response<cartidJSON> response) {
                        cartidJSON rs=response.body();
                        Intent intent=new Intent(ProductDetail.this,cart.class);
                        intent.putExtra("id",rs.getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<cartidJSON> call, Throwable t) {

                    }
                });

            }
        });
        btGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map=new HashMap<>();
                map.put("id",String.valueOf(1));
                map.put("productid",String.valueOf(productid));
                Call<cartidJSON> call=apiService.executeaddtocart(map);
                call.enqueue(new Callback<cartidJSON>() {
                    @Override
                    public void onResponse(Call<cartidJSON> call, Response<cartidJSON> response) {
                        cartidJSON rs=response.body();
                    }

                    @Override
                    public void onFailure(Call<cartidJSON> call, Throwable t) {

                    }
                });
                Toast.makeText(ProductDetail.this,"add success",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa(){
        Name=findViewById(R.id.Name);
        price=findViewById(R.id.Price);
        Description=findViewById(R.id.decription);
        viewPager= findViewById(R.id.viewpager);
        circleIndicator= findViewById(R.id.circle_indicator);
    }
    private List<Photo> getListPhoto(Product product){
        String imgname="img";
        imgname+=Integer.toString(product.getId());
        Resources res=getResources();

        List<Photo> list =new ArrayList<>();
        int resID=res.getIdentifier(imgname+"_1","drawable", getPackageName());
        list.add(new Photo(resID));
        resID=res.getIdentifier(imgname+"_2","drawable", getPackageName());
        list.add(new Photo(resID));
        resID=res.getIdentifier(imgname+"_3","drawable", getPackageName());
        list.add(new Photo(resID));
        return list;
    }
}