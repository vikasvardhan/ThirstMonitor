package com.example.thirstmonitor;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.example.thirstmonitor.MainWindow.MainActivity;
import com.example.thirstmonitor.Settings.PreferenceKey;

public class NotificationReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent repeatingIntent = new Intent(context, MainActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder =setupNotification(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(100, builder.build());
    }

    private  Notification.Builder setupNotification(Context context) {
      //  Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.drop_notification_icon);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon( R.drawable.drop_notification_icon).setContentTitle("Thirst Monitor")
                .setContentText("It is time to drink water")
                .setContentInfo("add a drink!").setAutoCancel(true);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean soundEnable= prefs.getBoolean(PreferenceKey.PREF_SOUND,false);

        if(soundEnable)
        {   builder.setDefaults(Notification.DEFAULT_SOUND);}
     return builder;
    }

}
