package com.phuocdai.demosourceandroid.utilities;

import android.text.TextUtils;
import android.util.Log;

import com.phuocdai.demosourceandroid.application.Constants;

/**
 * Created by phuocdai on 1/27/16.
 */
public class LogUtil {

    /**
     * LOG TAG.
     */
    private static final String APP_TAG = "PDDemoSourceAndroid";

    /**
     * Log info.
     *
     * @param tag tag name
     * @param msg message wants to log
     */
    public static void logError(final String tag, final String msg) {
        if (Constants.LOG_DEBUG && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    /**
     * Log debug.
     *
     * @param tag tag name
     * @param msg message wants to log
     */
    public static void logDebug(final String tag, final String msg) {
        if (Constants.LOG_DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    /**
     * Log debug.
     *
     * @param msg message wants to log
     */
    public static void logDebug(final String msg) {
        if (Constants.LOG_DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(APP_TAG, msg);
        }
    }

    /**
     * Log info.
     *
     * @param tag tag name
     * @param msg message wants to log
     */
    public static void logInfo(final String tag, final String msg) {
        if (Constants.LOG_DEBUG && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    /**
     * Log info.
     *
     * @param msg message wants to log
     */
    public static void logInfo(final String msg) {
        if (Constants.LOG_DEBUG && !TextUtils.isEmpty(msg)) {
            Log.i(APP_TAG, msg);
        }
    }
}
