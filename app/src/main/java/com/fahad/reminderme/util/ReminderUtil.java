package com.fahad.reminderme.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by moodi on 26/01/2018.
 */

public class ReminderUtil {

    private static long SECOND = 1000;
    double MINUTE = SECOND * 60;
    double HOUR = MINUTE * 60;
    double DAY = HOUR * 24;

    public static void StartTimeAlarmSet(Context context, long timeMilli, int IdOfCard) {

        Intent alarmIntent = new Intent(context, AlarmStartReciver.class);
        alarmIntent.putExtra("id", String.valueOf(IdOfCard));


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeMilli - 5 * SECOND,
                PendingIntent.getBroadcast(context, IdOfCard, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));

    }

    public static void StartTimeAlarmCancel(Context context, int IdOfCard) {
        Intent alarmIntent = new Intent(context, AlarmStartReciver.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(PendingIntent.getBroadcast(context, IdOfCard, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT));
    }
}
