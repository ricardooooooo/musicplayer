package com.example.musicplayer.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.example.musicplayer.R;
import com.example.musicplayer.Song;

import java.net.URI;
import java.net.URL;

public class CreateNotification {

    public static final String CHANNEL_ID = "channel1";
    public static final String ACTION_PLAY = "actionplay";

    public static Notification notification;

    public static void createNotification(Context context, Song song){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){


            NotificationManagerCompat notificationManagerCompact = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");


            notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_note)
                    .setContentTitle(song.getName())
                    .setContentText(song.getArtists())
                    .setOnlyAlertOnce(true)
                    .setOngoing(false)
                    .setShowWhen(true)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();




            notificationManagerCompact.notify(1,notification);



        }




    }
}
