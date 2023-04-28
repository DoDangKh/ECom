package com.example.ecom;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ProductViewHolder> {
    private List<Product> listProduct;

    public void setData(List<Product> list){
        this.listProduct=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product=listProduct.get(position);
        if(product==null) {
            return;
        }
        holder.img.setImageResource(product.getResourceid());
        holder.Name.setText(product.getName());
        holder.Price.setText(Integer.toString(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(listProduct!=null){
            return listProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView Name;
        private TextView Price;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.Image);
            Name=itemView.findViewById(R.id.Name);
            Price=itemView.findViewById(R.id.Price);
        }
    }
}
