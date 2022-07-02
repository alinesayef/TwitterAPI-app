package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

//method for viewing tweets
public class ViewAll extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBase db = new DataBase(this);
    List<Model> icList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        recyclerView = findViewById(R.id.All);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        icList = db.getAll();
        Tweet holidayAd = new Tweet(ViewAll.this,icList);
        recyclerView.setAdapter(holidayAd);
    }
}