package com.fahad.reminderme.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

/**
 * Created by moodi on 26/01/2018.
 */

public class TimeUtil {


    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    @SuppressLint("SimpleDateFormat")
    public static String parseTimeinLong(String timeStamp) {

        Timber.d("Get PArse" + timeStamp);
        return (new SimpleDateFormat("hh:mm:ss")).format(new Date(Long.valueOf(timeStamp)));
    }


}
