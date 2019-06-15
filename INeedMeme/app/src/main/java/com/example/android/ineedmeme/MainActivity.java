package com.example.android.ineedmeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView meme;
    String query = "https://www.google.co.in/search?biw=1366&bih=675&tbm=isch&sa=1&num=1000&ei=qFSJWsuTNc-wzwKFrZHoCw&q=dog%20meme";
    Elements cat_img;
    Elements dog_img;
    // GetImage getMeme = new GetImage();
    DataStruct dog_meme = new DataStruct();
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meme = (ImageView)findViewById(R.id.Meme_img);

        Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvSjy1IP9nHeZpyCEGyPW9tGXrjfrX7z6TA_U_oBrYaVGQk7Eefg")
                .resize(1000,1000)
                .into(meme);
        dog_meme.SA = new String[102];
        new GetImage(dog_meme).execute(query);
    }


    public void hey(View view) {
//        for (int i = 0; i < dog_meme.count; ++i){
//            Log.d("image-dog", dog_meme.SA[i]);
//        }
        Random r = new Random();
        int n = r.nextInt(dog_meme.count);
        Picasso.get()
                .load(dog_meme.SA[n])
                .resize(1000,1000)
                .into(meme);
    }
}
