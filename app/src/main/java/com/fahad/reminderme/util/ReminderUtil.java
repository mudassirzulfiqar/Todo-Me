package com.fahad.reminderme.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import timber.log.Timber;

/**
 * Created by moodi on 26/01/2018.
 */

public class ReminderUtil {

    private static long SECOND = 1000;
    double MINUTE = SECOND * 60;
    double HOUR = MINUTE * 60;
    double DAY = HOUR * 24;

    public static void StartTimeAlarmSet(Context context, String timeMilli, int IdOfCard, String title) {

        Intent alarmIntent = new Intent(context, AlarmStartReceiver.class);
        alarmIntent.putExtra("id", String.valueOf(IdOfCard));
        alarmIntent.putExtra("title", title);

        Timber.d("time : " + timeMilli);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Long.valueOf(timeMilli),
                PendingIntent.getBroadcast(context, IdOfCard, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));

    }

    public static void StartTimeAlarmCancel(Context context, int IdOfCard) {
        Intent alarmIntent = new Intent(context, AlarmStartReceiver.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(PendingIntent.getBroadcast(context, IdOfCard, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT));
    }
}
