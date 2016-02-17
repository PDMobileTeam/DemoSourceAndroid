package com.phuocdai.demosourceandroid.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by phuocdai on 2/17/16.
 */
public final class PrefUtil {
    private static final String TAG = "PrefUtil";

    /**
     *
     * @param con Context
     * @return Shared Preferences
     */
    private static SharedPreferences pref(Context con) {
        return PreferenceManager.getDefaultSharedPreferences(con);
    }

    /**
     * Get string by key
     *
     * @param con Context
     * @param key key to save data
     * @param defValue default value
     * @return String
     */
    public static String getString(Context con, String key, String defValue) {
        try {
            LogUtil.logInfo(TAG, "PrefUtils : getString : key = " + key);
            return pref(con).getString(key, defValue);
        } catch (Exception e) {
            LogUtil.logError(TAG, e.toString());
            return defValue;
        }
    }

    /**
     * Get boolean by key
     *
     * @param con Context
     * @param key key to save data
     * @param defValue default value
     * @return Boolean
     */
    public static boolean getBoolean(Context con, String key, boolean defValue) {
        try {
            LogUtil.logInfo(TAG, "PrefUtils : getBoolean : key = " + key);
            return pref(con).getBoolean(key, defValue);
        } catch (Exception e) {
            LogUtil.logError(TAG, e.toString());
            return defValue;
        }
    }

    /**
     * Write String
     *
     * @param con Context
     * @param key key to save data
     * @param value default value
     */
    public static void writeString(Context con, String key, String value) {
        try {
            LogUtil.logInfo(TAG, "PrefUtils : writeString : key = " + key
                    + ", value = " + value);
            pref(con).edit().putString(key, value).commit();
        } catch (Exception e) {
            LogUtil.logError(TAG, e.toString());
        }
    }

    /**
     * Write Boolean
     *
     * @param con Context
     * @param key key to save data
     * @param value default value
     */
    public static void writeBoolean(Context con, String key, boolean value) {
        try {
            LogUtil.logInfo(TAG, "PrefUtils : writeBoolean : key = " + key
                    + ", value = " + value);
            pref(con).edit().putBoolean(key, value).commit();
        } catch (Exception e) {
            LogUtil.logError(TAG, e.toString());
        }
    }

    /**
     * Clear all data
     *
     * @param con Context
     */
    public static void clearAll(Context con) {
        try {
            pref(con).edit().clear().commit();
        } catch (Exception e) {
            LogUtil.logError(TAG, e.toString());
        }
    }
}
