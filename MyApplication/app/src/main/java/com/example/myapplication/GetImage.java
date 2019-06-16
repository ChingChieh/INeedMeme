package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class GetImage extends AsyncTask<String, Void, String> {

    int array_size = 102;

    Document doc = null;
    Elements imgs;
    String[] img_url = new String[array_size];
    private WeakReference<DataStruct> mDataStruct;

    GetImage(DataStruct DS) {
        mDataStruct = new WeakReference<>(DS);
        mDataStruct.get().SA = new String[array_size];
    }


    @Override
    protected String doInBackground(String... strings) {
        getImages(strings[0]);
        Log.d("image-gg", img_url[0]);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // mDataStruct.get().SA = img_url;
        Log.d("image-gg-count", String.valueOf(mDataStruct.get().count));
        for (int i = 0; i < mDataStruct.get().count; ++i){
            mDataStruct.get().SA[i] = img_url[i];
        }
        mDataStruct.get().accessible = 1;
        MemeActivity.nextmeme.setEnabled(true);
    }

    private void getImages(String url) {

        try{
            doc = Jsoup.connect(url).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        int count = 0;
        imgs = doc.select("img");
        System.out.println("Damn images"+imgs);
        for (Element img : imgs){
            if (count >= array_size){
                break;
            }
            if (img.attr("data-src").length() > 1) {
                img_url[count] = img.attr("data-src");
                count++;
            }

            // Log.d("image-src", img.ownText());
            Log.d("image-src", img.attr("data-src"));//changed `src` to `data-src`

        }
        Log.d("image-count", String.valueOf(count));
        //  Log.d("image-tail", img_url[101]);
        mDataStruct.get().count = count;
    }

}

