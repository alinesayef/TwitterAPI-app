package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

//list tweets available for edit
public class Edit extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBase db = new DataBase(this);
    List<Model> icList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        recyclerView = findViewById(R.id.All);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        icList = db.getAll();
        Tweet2 holidayAd = new Tweet2(Edit.this,icList);
        recyclerView.setAdapter(holidayAd);

    }
}