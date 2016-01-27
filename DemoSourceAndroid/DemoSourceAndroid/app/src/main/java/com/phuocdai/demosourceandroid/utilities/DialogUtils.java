package com.phuocdai.demosourceandroid.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by phuocdai on 1/27/16.
 */
public class DialogUtils {
    /**
     *  Create Alert with three button
     */
    public static AlertDialog createAlert(Context context, String title, String message,
                                          String neutral, DialogInterface.OnClickListener neutralCallback,
                                          String positive, DialogInterface.OnClickListener positiveCallback,
                                          String negative, DialogInterface.OnClickListener negativeCallback){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        if (title != null) {
            alertDialogBuilder.setTitle(title);
        }

        // set message
        if (message != null) {
            alertDialogBuilder.setMessage(message);
        }

        // set neutral button
        if (neutral != null && neutralCallback != null) {
            alertDialogBuilder.setNeutralButton(neutral, neutralCallback);
        }

        // set positive button
        if (positive != null && positiveCallback != null) {
            alertDialogBuilder.setPositiveButton(positive, positiveCallback);
        }

        // set negative button
        if (negative != null && negativeCallback != null) {
            alertDialogBuilder.setNegativeButton(negative, negativeCallback);
        }

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // can not cancel alert when press Back button
        alertDialog.setCancelable(false);
        // can not touch out site to cancel alert
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    /**
     * Create alert with two button
     */
    public static AlertDialog createAlert(Context context, String title, String message,
                                          String positive, DialogInterface.OnClickListener positiveCallback,
                                          String negative, DialogInterface.OnClickListener negativeCallback) {
        return createAlert(context, title, message, null, null, positive, positiveCallback, negative, negativeCallback);
    }

    /**
     * Create alert with one button
     */
    public static AlertDialog createAlert(Context context, String title, String message,
                                          String negative, DialogInterface.OnClickListener negativeCallback) {
        return createAlert(context, title, message, null, null, null, null, negative, negativeCallback);
    }
}
