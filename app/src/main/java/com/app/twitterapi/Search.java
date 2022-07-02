
package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//method that allows searching for tweets
public class Search extends AppCompatActivity {
    DataBase db = new DataBase(this);
    EditText Search;
    TextView Title,Content,Writter;
    ImageView imageView;
    Model ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Search = findViewById(R.id.Name);
        Title = findViewById(R.id.Title);
        Content = findViewById(R.id.Content);
        Writter = findViewById(R.id.Write);
        imageView = findViewById(R.id.Image);
    }

    public void Search(View view) {
        ic = db.SearchByName(Search.getText().toString());
        if(ic == null){
            Title.setText("Not Found");
            Writter.setText("Not Found");
            Content.setText("Not Found");
        }
        else{
            Title.setText(ic.getName());
            Content.setText(ic.getContent());
            Writter.setText(ic.getWriter());
            imageView.setImageBitmap(ic.getImage());
        }
    }
}