package com.example.ecom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    DBhelper db=new DBhelper(this);
    List<String> listData=new ArrayList<String>();
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    ListView toolbarlv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        setContentView(R.layout.activity_main);
//        db.QueryData("insert into Products Values(null,'adidas shoes','high quality adidas shoes',60)");
        initDB();
        anhXa();
        actionToolBar();
        Cursor data=db.GetData("Select * From Products");
        ArrayList<Product> listProduct=new ArrayList<>();
        while(data.moveToNext()){
            Product p=new Product();
            p.setId(data.getInt(0));
            p.setName(data.getString(1));
            p.setDescription(data.getString(2));
            p.setPrice(data.getInt(3));
            listProduct.add(p);
        }
        for(int i=0;i<listProduct.size();i++){
            Log.d("CREATION",listProduct.get(i).getName());
            Log.d("CREATION",Integer.toString(listProduct.get(i).getPrice()));
            listData.add(listProduct.get(i).getName());
        }
        Log.d("CREATION",Integer.toString(listData.size()));
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int value=0;
                Log.d("CREATION","i="+i);

                for(int j=0;j<listProduct.size();j++){
                    Log.d("CREATION","j="+j);
                    if(arrayAdapter.getItem(i).toString().equals(listProduct.get(j).getName())){
                        value=listProduct.get(j).getId();

                        break;
                    }

                }
                Intent intent=new Intent(MainActivity.this,ProductDetail.class);
                intent.putExtra("vl",Integer.toString(value));
                startActivity(intent);
            }
        });


//        binding.listview.setAdapter(listAdapter);

    }

    private void initDB (){
        db.QueryData("Create Table If Not Exists Orders(Id Integer Primary Key AUTOINCREMENT,IDCustomer VARCHAR(50),ordertime VARCHAR(50))");
        db.QueryData("Create Table If Not Exists Orderdetail(Id Integer Primary Key AUTOINCREMENT,Idorder Integer, Idproduct Integer, amount Integer)");
        db.QueryData("Create Table If Not Exists Carts(Id Integer Primary Key AUTOINCREMENT,IdCustomer Integer,Status Integer )");
        db.QueryData("Create Table If Not Exists CartDetail(Id Integer Primary Key AUTOINCREMENT,Idcart Integer, Idproduct Integer, amount Integer)");
        db.QueryData("Delete From Carts");
        db.QueryData("Delete From CartDetail");
    }
    private void anhXa(){
        listView= (ListView) findViewById(R.id.list);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        navigationView=(NavigationView) findViewById(R.id.navigationview);
        toolbarlv=(ListView) findViewById(R.id.navigation_listv);
    }
    public void actionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_name_menu);
        String[] listtool={"Home","Cart"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listtool);
        toolbarlv.setAdapter(arrayAdapter);
        toolbarlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    Cursor data=db.GetData("Select * From Carts where idCustomer=1");
                    int id=0;
                    while(data.moveToNext()){
                        id=data.getInt(0);
                    }
                    Intent intent=new Intent(MainActivity.this,cart.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}