package com.fahad.reminderme.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.fahad.reminderme.MainActivity;

import timber.log.Timber;

/**
 * Created by moodi on 26/01/2018.
 */

public class AlarmStartReceiver extends WakefulBroadcastReceiver {


    private String title;
    private Integer id;

    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO: 26/01/2018 Get Name and from Database

        Timber.d("Alarm on");

        id = intent.getIntExtra("id", 12);
        title = intent.getStringExtra("title");

        createNotification(context, title, "Reminder", id);
    }


    public void createNotification(Context context, String title, String text, int id) {

        Intent intent = new Intent(context, MainActivity.class);

        //   intent.putExtra("do_action", "play");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setContentTitle(title);

        notification.setContentIntent(pendingIntent);
        notification.setAutoCancel(true);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        notificationManager.notify(id, notification.build());

    }


}