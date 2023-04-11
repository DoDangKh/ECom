package com.example.ecom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class Photoadapter extends PagerAdapter  {

    private Context mContext;
    private List<Photo> listPhoto;

    public Photoadapter(Context mContext, List<Photo> listPhoto) {
        this.mContext = mContext;
        this.listPhoto = listPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imgPhoto= view.findViewById(R.id.imageView);
        Photo photo=listPhoto.get(position);
        if(photo!=null){
            Glide.with(mContext).load(photo.getResourceID()).into(imgPhoto);

        }

        container.addView(view);


        return view;
    }

    @Override
    public int getCount() {
        if(listPhoto!= null){
            return listPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
