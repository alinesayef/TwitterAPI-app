package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//method for editing the tweet
public class Edited extends AppCompatActivity {

    DataBase db = new DataBase(this);
    Model ic;
    EditText Title,Content,Writter;
    int id;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edited);
        id  = getIntent().getExtras().getInt("id");
        ic = db.Search(id);
        Title = findViewById(R.id.Name);
        Content = findViewById(R.id.Content);
        Writter = findViewById(R.id.Write);
        Title.setText(ic.getName());
        Content.setText(ic.getContent());
        Writter.setText(ic.getWriter());
    }

//method for updating the database
    public void Update(View view) {
        db.Edit(String.valueOf(id),Title.getText().toString(),Writter.getText().toString(),Content.getText().toString(),ic.getImage());
        Toast.makeText(Edited.this, "Edited !!", Toast.LENGTH_SHORT).show();


    }
}