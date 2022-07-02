package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//method for manage messages menu
public class Manage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
    }

    // The methods below use an intent, to start an activity, to create, edit, delete or search tweets.
    public void Create(View view) {
        Intent intent = new Intent(this,Create.class);
        startActivity(intent);
    }
    
    public void Edit(View view) {
        Intent intent = new Intent(this,Edit.class);
        startActivity(intent);
    }
    public void Delete(View view) {
        Intent intent = new Intent(this,Delete.class);
        startActivity(intent);
    }

    public void Search(View view) {
        Intent intent = new Intent(this,Search.class);
        startActivity(intent);

    }
}