package com.fahad.reminderme.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.fahad.reminderme.MainActivity;

/**
 * Created by moodi on 26/01/2018.
 */

public class AlarmStartReceiver extends BroadcastReceiver {

    private String Message;
    private String CardType;
    private String id;
    private String Switch;
    private String Title;

    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO: 26/01/2018 Get Name and from Database

//        createNotification(context, );

    }


    public void createNotification(Context context, String title, String text, int icon, int id) {

        Intent intent = new Intent(context, MainActivity.class);

        //   intent.putExtra("do_action", "play");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setSmallIcon(icon)
                .setOngoing(true);

        notification.setContentIntent(pendingIntent);
        notification.setAutoCancel(false);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        notificationManager.notify(id, notification.build());

    }


}