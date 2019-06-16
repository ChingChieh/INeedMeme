package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.jsoup.select.Elements;

import java.util.Random;

public class MemeActivity extends AppCompatActivity {

    ImageView meme;
    String query = "https://www.google.co.in/search?biw=1366&bih=675&tbm=isch&sa=1&num=1000&ei=qFSJWsuTNc-wzwKFrZHoCw&q=";
    public static Button nextmeme;
    // GetImage getMeme = new GetImage();
    DataStruct meme_struct = new DataStruct();
    int search_ready = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);
        meme = (ImageView)findViewById(R.id.Meme_img);
        /*防止網路處理未完成就access的情形*/
        nextmeme = findViewById(R.id.button5);
        nextmeme.setEnabled(false);

        meme_struct.SA = new String[102];
        meme_struct.accessible = 0;
        Intent intent = getIntent();
        String message = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);
        query = query + message + "%20meme";
        new GetImage(meme_struct).execute(query);

        //Random r = new Random();
        //int n = r.nextInt(meme_struct.count);
        Log.d("query",query);

    }


    public void showMeme(View view) {
        Random r = new Random();
        int n = r.nextInt(meme_struct.count);
        Picasso.get()
                .load(meme_struct.SA[n])
                .resize(1000, 1000)
                .into(meme);
    }

    public void Back2search(View view) {
        Intent intent;
        intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
