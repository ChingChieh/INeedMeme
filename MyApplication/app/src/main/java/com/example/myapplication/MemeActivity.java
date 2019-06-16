package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.jsoup.select.Elements;

import java.util.Random;

public class MemeActivity extends AppCompatActivity {

    ImageView meme;
    String query = "https://www.google.co.in/search?biw=1366&bih=675&tbm=isch&sa=1&num=1000&ei=qFSJWsuTNc-wzwKFrZHoCw&q=";
    // GetImage getMeme = new GetImage();
    DataStruct meme_struct = new DataStruct();
    int search_ready = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);
        meme = (ImageView)findViewById(R.id.Meme_img);

        meme_struct.SA = new String[102];
        meme_struct.accessible = 0;
        Intent intent = getIntent();
        String message = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);
        query = query + message + "%20meme";
        new GetImage(meme_struct).execute(query);

        //Random r = new Random();
        //int n = r.nextInt(meme_struct.count);
        Log.d("query",query);

//        for(int i = 0; i < 80; i++){
//            Log.d("WTF",meme_struct.SA[i]);
//        }



    }


    public void showMeme(View view) {
        Picasso.get()
                .load(meme_struct.SA[20])
                .resize(1000, 1000)
                .into(meme);
    }
}
