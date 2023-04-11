package com.example.ecom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Photoadapter photoadapter;
    private TextView Name;
    private TextView Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        setContentView(R.layout.activity_main);
        Name=findViewById(R.id.Name);
        Description=findViewById(R.id.decription);
        Name.setText("Nike Sneakers");
        Description.setText("Perhaps the most iconic sneaker of all-time, this original \"Chicago\"? colorway is the cornerstone to any sneaker collection. Made famous in 1985 by Michael Jordan, the shoe has stood the test of time, becoming the most famous colorway of the Air Jordan 1. This 2015 release saw the ...More");
        viewPager= findViewById(R.id.viewpager);
        circleIndicator= findViewById(R.id.circle_indicator);

        photoadapter= new Photoadapter(this, getListPhoto());
        viewPager.setAdapter(photoadapter);

        circleIndicator.setViewPager(viewPager);
        photoadapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


    }
    private List<Photo> getListPhoto(){
        List<Photo> list =new ArrayList<>();
        list.add(new Photo(R.drawable.img1));
        list.add(new Photo(R.drawable.img2));
        list.add(new Photo(R.drawable.img3));
        return list;
    }
}