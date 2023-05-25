package com.example.ecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.ecom.API.ListProductJSON;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Console;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Statistic extends AppCompatActivity {
    private Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiService apiService;
    ArrayList barArraylist=new ArrayList<BarEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        apiService=retrofit.create(ApiService.class);


        Call<ListProductJSON> call=apiService.executeGetAllProduct();
        call.enqueue(new Callback<ListProductJSON>() {
            @Override
            public void onResponse(Call<ListProductJSON> call, Response<ListProductJSON> response) {
                ListProductJSON rs=response.body();
                BarChart barChart= findViewById(R.id.barchart);

                for(int i=0;i<rs.getListProduct().size();i++){
                    barArraylist=new ArrayList();
                    Log.d("CreationStatistic",String.valueOf(i));
                    Log.d("CreationStatistic",String.valueOf(rs.getListProduct().get(i).getSoluong()));
                    BarEntry barEntry=new BarEntry(i,rs.getListProduct().get(i).getSoluong());

                    barArraylist.add(barEntry);
//                    barArraylist.add(new BarEntry(2f,100));
                    Log.d("CreationStatistic",String.valueOf(barArraylist.size()));

                }
                BarDataSet barDataSet=new BarDataSet(barArraylist,"Product Amount");
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                BarData barData=new BarData(barDataSet);
                barData.setDrawValues(false);
                barChart.setData(barData);
                barChart.invalidate();
;               barChart.refreshDrawableState();
            }

            @Override
            public void onFailure(Call<ListProductJSON> call, Throwable t) {

            }
        });
    }
}