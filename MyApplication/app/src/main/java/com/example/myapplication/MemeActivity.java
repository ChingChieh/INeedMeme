package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.jsoup.select.Elements;

public class MemeActivity extends AppCompatActivity {

    ImageView meme;
    String query = "https://www.google.co.in/search?biw=1366&bih=675&tbm=isch&sa=1&num=1000&ei=qFSJWsuTNc-wzwKFrZHoCw&q=";
    // GetImage getMeme = new GetImage();
    DataStruct dog_meme = new DataStruct();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);
        meme = (ImageView)findViewById(R.id.Meme_img);
        Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvSjy1IP9nHeZpyCEGyPW9tGXrjfrX7z6TA_U_oBrYaVGQk7Eefg")
                .resize(1000,1000)
                .into(meme);
        dog_meme.SA = new String[102];
        Intent intent = getIntent();
        String message = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);
        query = query + message + "%20meme";
        new GetImage(dog_meme).execute(query);
    }


}
