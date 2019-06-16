package com.example.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import static com.example.myapplication.Main3Activity.meme_struct;


public class MyReceiver extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    Integer len = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        len = intent.getIntExtra("listlen",0);

        deliverNotification(context);
    }
    private void deliverNotification(final Context context) {


        Intent contentIntent = new Intent(context, MainActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Meme Time!")
                .setContentText("Get back to get more meme!")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);




        Bitmap androidImage = BitmapFactory
                .decodeResource(context.getResources(),R.drawable.image);

        Bitmap memeimage = null;
        memeimage = downloadImageBitmap(meme_struct.SA[len]);
        //Integer index = (len++)%len;
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidImage));
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());


    }

    private Bitmap downloadImageBitmap(String sUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(sUrl);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());      // Decode Bitmap
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
