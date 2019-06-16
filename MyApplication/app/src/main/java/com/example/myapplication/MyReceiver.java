package com.example.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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

import static com.example.myapplication.Main3Activity.topicid;


public class MyReceiver extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);
    }
    private void deliverNotification(final Context context) {
        int catimg[] = {R.drawable.cat1,R.drawable.cat2,R.drawable.cat3,R.drawable.cat4,R.drawable.cat5,R.drawable.cat6,R.drawable.cat7,R.drawable.cat8,
                R.drawable.cat9,R.drawable.cat10,R.drawable.cat11,R.drawable.cat12,R.drawable.cat13,R.drawable.cat14,R.drawable.cat15};
        int dogimg[] = {R.drawable.dog1,R.drawable.dog2,R.drawable.dog3,R.drawable.dog4,R.drawable.dog5,R.drawable.dog6,R.drawable.dog7,R.drawable.dog8,
                R.drawable.dog9,R.drawable.dog10,R.drawable.dog11,R.drawable.dog12,R.drawable.dog13,R.drawable.dog14,R.drawable.dog15};
        Random r = new Random();
        int n = r.nextInt(catimg.length);
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
            Bitmap androidImage = null;
            if(topicid == 0){
                androidImage = BitmapFactory
                        .decodeResource(context.getResources(),catimg[n]);
            }
            else if(topicid == 1){
                androidImage = BitmapFactory
                        .decodeResource(context.getResources(),dogimg[n]);
            }
            else{
                androidImage = BitmapFactory
                        .decodeResource(context.getResources(),R.drawable.image);
            }

            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidImage));
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());

        /*PendingIntent contentPendingIntent = PendingIntent.getActivity
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
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidImage));
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());*/
    }
}
