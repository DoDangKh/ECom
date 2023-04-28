package com.example.ecom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {
    private RecyclerView recv;
    DBhelper db=new DBhelper(this);
//    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_cart);
        recv=findViewById(R.id.rcv);
        ListAdapter listAdapter=new ListAdapter();
        String imgname="img";

        Resources res=getResources();
        int id=getIntent().getIntExtra("id",0);
        Log.d("CREATION",Integer.toString(id));

        Cursor data=db.GetData("Select * From cartdetail where idcart="+id);
        ArrayList<Integer> listid=new ArrayList<>();
        while(data.moveToNext()){
            listid.add(data.getInt(2));
        }
        ArrayList<Product> listProduct=new ArrayList<>();
        for(int i=0;i<listid.size();i++){
//            int tempi=listid.get(i);
            imgname="img";
            data=db.GetData("Select * from Products where id="+listid.get(i));
            Product p=new Product();
            data.moveToNext();
            p.setId(data.getInt(0));
            p.setName(data.getString(1));
            p.setDescription(data.getString(2));
            p.setPrice(data.getInt(3));
            imgname+=Integer.toString(p.getId())+"_1";
            p.setResourceid(res.getIdentifier(imgname,"drawable", getPackageName()));
            listProduct.add(p);
        }

        listAdapter.setData(listProduct);

        recv.setAdapter(listAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recv.setLayoutManager(linearLayoutManager);

    }
}