package com.app.twitterapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Tweet extends RecyclerView.Adapter<Tweet.MyViewHolder> {

    Context mContext;
    List<Model> Data;
    public Tweet(Context mContext, List<Model> data) {
        this.mContext = mContext;
        Data = data;
    }

    @NonNull
    @Override
    public Tweet.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.viewar,parent,false);
        return new Tweet.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Tweet.MyViewHolder holder, int position) {
        holder.Name.setText(Data.get(position).getName());
        holder.Desc.setText(Data.get(position).getWriter());
        holder.imageView.setImageBitmap(Data.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name,Desc;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Desc = itemView.findViewById(R.id.Desc);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetails = new Intent(mContext, Details.class);
                    postDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    int pos = getAdapterPosition();
                    postDetails.putExtra("id",Data.get(pos).getT1());
                    mContext.startActivity(postDetails);

                }
            });
        }
    }


}
