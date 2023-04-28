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

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetail extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Photoadapter photoadapter;
    private TextView Name;
    private TextView Description;
    private TextView price;
    private DBhelper db=new DBhelper(this);
    private Button btbuynow;

    private Button btGoToCart;

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
        Cursor data=db.GetData("Select * From Products where id ="+vl);
        Product p=new Product();
        while(data.moveToNext()){
            p.setId(data.getInt(0));
            p.setName(data.getString(1));
            p.setDescription(data.getString(2));
            p.setPrice(data.getInt(3));
        }
        Name=findViewById(R.id.Name);
        Log.d("Creation",Integer.toString(p.getPrice()));
        price=findViewById(R.id.Price);
        price.setText(Integer.toString(p.getPrice()));
        Description=findViewById(R.id.decription);
        Name.setText(p.getName());
        Description.setText(p.getDescription());
        viewPager= findViewById(R.id.viewpager);
        circleIndicator= findViewById(R.id.circle_indicator);

        photoadapter= new Photoadapter(this, getListPhoto(p));
        viewPager.setAdapter(photoadapter);

        circleIndicator.setViewPager(viewPager);
        photoadapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        btbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=0;
                Cursor data= db.GetData("Select * From Carts where status=1 and idCustomer=1");

                if(data.getCount()==0){
                    db.QueryData("Insert into Carts Values(null,1,1)");
                    Cursor data2= db.GetData("Select * From Carts where status=1 and idCustomer=1");
                    while(data2.moveToNext()){
                        id=data2.getInt(0);
                    }
                    db.QueryData("Insert into  cartdetail Values(null,"+id+","+p.getId()+",1)");
                }
                else {
                    while (data.moveToNext()) {
                        id = data.getInt(0);
                    }
                    Cursor data2 = db.GetData("Select * From cartdetail where idcart=" + id + " and idproduct=" + p.getId());
                    if (data2.getCount() == 0) {
                        db.QueryData("Insert into cartdetail Values (null," + id + "," + p.getId() + ",1)");

                    }
                    else{
                        data2=db.GetData("Select * From Cartdetail where idcart="+id+" and idproduct="+p.getId());
                        int amount=data2.getInt(3);
                        amount+=1;
                        db.QueryData("update cartdetail set amount="+amount+"where idcart="+id+" and idproduct="+p.getId());
                    }
                }


                Intent intent=new Intent(ProductDetail.this,cart.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        btGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=0;
                Cursor data= db.GetData("Select * From Carts where status=1 and idCustomer=1");

                if(data.getCount()==0){
                    db.QueryData("Insert into Carts Values(null,1,1)");
                    Cursor data2= db.GetData("Select * From Carts where status=1 and idCustomer=1");
                    while(data2.moveToNext()){
                        id=data2.getInt(0);
                    }
                    db.QueryData("Insert into  cartdetail Values(null,"+id+","+p.getId()+",1)");
                }
                else {
                    while (data.moveToNext()) {
                        id = data.getInt(0);
                    }
                    Cursor data2 = db.GetData("Select * From cartdetail where idcart=" + id + " and idproduct=" + p.getId());
                    if (data2.getCount() == 0) {
                        db.QueryData("Insert into cartdetail Values (null," + id + "," + p.getId() + ",1)");

                    }
                    else{
                        data2=db.GetData("Select * From Cartdetail where idcart="+id+" and idproduct="+p.getId());
                        int amount=data2.getInt(3);
                        amount+=1;
                        db.QueryData("update cartdetail set amount="+amount+"where idcart="+id+" and idproduct="+p.getId());
                    }
                }
                Toast.makeText(ProductDetail.this,"add success",Toast.LENGTH_SHORT).show();
            }
        });
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