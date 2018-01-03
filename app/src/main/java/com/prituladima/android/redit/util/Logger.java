package com.prituladima.android.redit.util;

import android.util.Log;

import com.prituladima.android.redit.BuildConfig;

public class Logger {

    public Logger(Class c) {
        logTag = c.getSimpleName();
    }

    private String logTag;
    private boolean isLogging = BuildConfig.SHOW_LOGS;

    public void log(String message) {
        if (isLogging)
            Log.d(logTag, message);
    }

    public void error(Throwable message) {
        if (isLogging)
            Log.e(logTag, message.toString());
    }

}