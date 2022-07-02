package com.app.twitterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class Details extends AppCompatActivity {
    DataBase db = new DataBase(this);
    TextView Title,Content,Writter;
    ImageView imageView;
    Model ic;
    TwitterLoginButton loginButton;
    public static int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Twitter.initialize(this);
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                //String token = authToken.token;
                //  String secret = authToken.secret;

                loginMethod(session);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(getApplicationContext(),"Login fail",Toast.LENGTH_LONG).show();
            }
        });
        id  = getIntent().getExtras().getInt("id");
        ic = db.Search(id);
        Title = findViewById(R.id.Title);
        Content = findViewById(R.id.Content);
        Writter = findViewById(R.id.Write);
        imageView = findViewById(R.id.Image);
        Title.setText(ic.getName());
        Content.setText(ic.getContent());
        Writter.setText(ic.getWriter());
        imageView.setImageBitmap(ic.getImage());
    }

    //share the tweet
    public void Share(View view) {
        String tweetUrl = "https://twitter.com/intent/tweet?text="+ic.getContent()+"\n Written By : "+ic.getWriter();
        Uri uri = Uri.parse(tweetUrl);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }

    //login method for Twitter
    public void loginMethod(TwitterSession twitterSession){
        String userName=twitterSession.getUserName();
        Intent intent= new Intent(Details.this,HomeActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("username",userName);

        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    //share via email method
    public void ShareM(View view) {
        Intent intent= new Intent(Details.this,Mail.class);
        intent.putExtra("id",id);

        startActivity(intent);
    }
}