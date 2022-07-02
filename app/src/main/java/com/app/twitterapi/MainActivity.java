package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method for managing tweets
    public void Manage(View view) {
        Intent intent = new Intent(this,Manage.class);
        startActivity(intent);
    }

    //method for viewing tweets
    public void All(View view) {
        Intent intent = new Intent(this,ViewAll.class);
        startActivity(intent);
    }

}