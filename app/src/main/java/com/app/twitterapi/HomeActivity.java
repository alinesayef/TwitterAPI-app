package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Model ic;
    DataBase db = new DataBase(this);

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Details.id  = getIntent().getExtras().getInt("id");
        ic = db.Search(Details.id);

    }
    public void Share(View view) {
        String tweetUrl = "https://twitter.com/intent/tweet?text="+ic.getContent()+"\nBy : "+ic.getWriter();
        Uri uri = Uri.parse(tweetUrl);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }
}