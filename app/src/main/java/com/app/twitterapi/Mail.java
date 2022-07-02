package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Mail extends AppCompatActivity {
    EditText editText;
    Model ic;
    int id;
    DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        editText = findViewById(R.id.editTextTextPersonName);
        id  = getIntent().getExtras().getInt("id");
        ic = db.Search(id);
    }
//send email method
    public void Send(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{editText.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT,ic.getName());
        intent.putExtra(Intent.EXTRA_TEXT,ic.getContent());
        intent.setData(Uri.parse("mailto:"));
        startActivity(intent);

    }
}