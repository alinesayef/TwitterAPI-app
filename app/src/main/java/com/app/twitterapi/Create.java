package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Create extends AppCompatActivity {
    ImageView imgview;
    Uri FilePathUri;
    Bitmap bitmap;
    DataBase db = new DataBase(this);
    EditText Name,Content,Write;
    String N,C,W;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        imgview = findViewById(R.id.imageView);
        Name = findViewById(R.id.Name);
        Content = findViewById(R.id.Content);
        Write = findViewById(R.id.Write);

    }
    //method to choose an image
    public void Choose(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 7);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    // saves information to the database
    public void Save(View view) {
        if(FilePathUri==null)Toast.makeText(Create.this, "Add an image!", Toast.LENGTH_SHORT).show();
        else {
            N = Name.getText().toString();
            C = Content.getText().toString();
            W = Write.getText().toString();
            Model model = new Model(N, C, W, bitmap);
            db.Store(model);
            Toast.makeText(Create.this, "Added!", Toast.LENGTH_SHORT).show();
        }
    }


}