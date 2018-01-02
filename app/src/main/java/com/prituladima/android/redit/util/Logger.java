package com.prituladima.android.redit.util;

import android.util.Log;

import com.prituladima.android.redit.BuildConfig;

public class Logger {

    public Logger(Class c) {
        logTag = c.getSimpleName();
        isLogging = BuildConfig.SHOW_LOGS;
    }

    private String logTag;
    private boolean isLogging = false;

    public void log(String message) {
        if (isLogging)
            Log.d(logTag, message);
    }

    public void error(String message) {
        if (isLogging)
            Log.e(logTag, message);
    }

    public void info(String message) {
        if (isLogging)
            Log.i(logTag, message);
    }

}
