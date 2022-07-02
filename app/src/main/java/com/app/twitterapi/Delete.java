package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

//list tweets available for deletion
public class Delete extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBase db = new DataBase(this);
    List<Model> icList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        recyclerView = findViewById(R.id.All);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        icList = db.getAll();
        Tweet1 holidayAd = new Tweet1(Delete.this,icList);
        recyclerView.setAdapter(holidayAd);
    }
}