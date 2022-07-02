package com.app.twitterapi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Tweet1 extends RecyclerView.Adapter<Tweet1.MyViewHolder> {

    Context mContext;

    List<Model> Data;
    public Tweet1(Context mContext, List<Model> data) {
        this.mContext = mContext;
        Data = data;
    }

    @NonNull
    @Override
    public Tweet1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.viewar,parent,false);
        return new Tweet1.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Tweet1.MyViewHolder holder, int position) {
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

                //method for deleting a tweet
                public void onClick(View v) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Delete this Tweet ?")
                            .setMessage("This Tweet will be deleted.")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    DataBase db = new DataBase(mContext);

                                    int pos = getAdapterPosition();
                                    db.Delete(String.valueOf(Data.get(pos).getT1()));
                                    Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext,Delete.class);
                                    mContext.startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

                }
            });
        }
    }


}
