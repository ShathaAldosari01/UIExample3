package com.example.uiexample3;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/*notification ~ s1 | create channel*/
public class Notf extends Application {
    /*1*/
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2 ";

    /*2*/
    @Override
    public void onCreate(){
        super.onCreate();
        /*3.1*/
        createNotificationChannels();
    }

    /*3.2*/
    public void createNotificationChannels(){
        /*1 ~ know the version -> diffrent for each version*/
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            /*2.1 ~ Create the channel1*/
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "reminders", //name of the channel. note the user can see it
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("this channel for reminders");
            /*2.2 ~ Create the channel2*/
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "other", //name of the channel. note the user can see it
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("this channel for things other then reminders");

            /*3.1 ~ Get the notification manger*/
            NotificationManager manager = getSystemService(NotificationManager.class);
            /*3.2 ~ Pass the channal to notification manger*/
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }

    }
}
/*end notification ~ s1 | create channel
* next step in "app> manifests> AndroidManifest.xml"*/