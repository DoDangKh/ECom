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

import com.example.ecom.API.ListProductJSON;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    DBhelper db=new DBhelper(this);
    List<String> listData=new ArrayList<String>();
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    ListView toolbarlv;
    ApiService apiService;
    ArrayList<Product> listProduct=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        setContentView(R.layout.activity_main);
//        db.QueryData("insert into Products Values(null,'adidas shoes','high quality adidas shoes',60)");
//        initDB();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService=retrofit.create(ApiService.class);

        Call<ListProductJSON> call=apiService.executeGetAllProduct();
//        Log.d("CREATIONAPIV1","TEST");

        call.enqueue(new Callback<ListProductJSON>() {
            @Override
            public void onResponse(Call<ListProductJSON> call, Response<ListProductJSON> response) {
//                Log.d("CREATIONAPIV1","TEST");
                ListProductJSON rs=response.body();

                for(int i=0;i<rs.getListProduct().size();i++){
                    Log.d("CREATIONAPIV1",rs.getListProduct().get(i).getName());
                }
                listProduct=rs.getListProduct();
                for(int i=0;i<listProduct.size();i++){
                    Log.d("CREATION",listProduct.get(i).getName());
                    Log.d("CREATION",Integer.toString(listProduct.get(i).getPrice()));
                    listData.add(listProduct.get(i).getName());
                }
                Log.d("CREATIONSize",Integer.toString(listData.size()));
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,listData);
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
            }

            @Override
            public void onFailure(Call<ListProductJSON> call, Throwable t) {
                Log.d("CREATIONAPIV1",t.getMessage());
            }
        });

        anhXa();
        actionToolBar();
//        Cursor data=db.GetData("Select * From Products");
//        ArrayList<Product> listProduct=new ArrayList<>();
//        while(data.moveToNext()){
//            Product p=new Product();
//            p.setId(data.getInt(0));
//            p.setName(data.getString(1));
//            p.setDescription(data.getString(2));
//            p.setPrice(data.getInt(3));
//            listProduct.add(p);
//        }





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
        String[] listtool={"Home","Cart","Statistic"};
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
                if(i==2){
                    Intent intent=new Intent(MainActivity.this,Statistic.class);
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