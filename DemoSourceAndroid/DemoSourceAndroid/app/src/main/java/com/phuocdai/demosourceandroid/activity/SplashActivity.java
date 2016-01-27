package com.phuocdai.demosourceandroid.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.phuocdai.demosourceandroid.R;
import com.phuocdai.demosourceandroid.application.Constants;
import com.phuocdai.demosourceandroid.pushNotification.RegistrationIntentService;
import com.phuocdai.demosourceandroid.utilities.DialogUtils;
import com.phuocdai.demosourceandroid.utilities.LogUtil;

public class SplashActivity extends BaseActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupView() {
        super.setupView();
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkConnected()) {

            mRegistrationBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    SharedPreferences sharedPreferences =
                            PreferenceManager.getDefaultSharedPreferences(context);
                    boolean sentToken = sharedPreferences
                            .getBoolean(Constants.SENT_TOKEN_TO_SERVER, false);
                    String dToken = sharedPreferences.getString(Constants.NOTIF_REGISTRATION_ID, "");
                    LogUtil.logInfo("===Token:" + dToken);
                    if (sentToken) {
                        LogUtil.logInfo("HAS DEVICE TOKEN");
                        startSplash();

                    } else {
                        LogUtil.logInfo("TOKEN IS NULL");
                    }
                }
            };

            if (checkPlayServices()) {
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }

            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Constants.NOTIF_REGISTRATION_COMPLETE));
        } else {
            DialogUtils.createAlert(mContext, null, "Error network"
                    , "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If it
     * doesn't, display a dialog that allows users to download the APK from the
     * Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(
                        resultCode,
                        this,
                        PLAY_SERVICES_RESOLUTION_REQUEST
                ).show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Check token and start activity
     */
    private void startSplash(){
        //TODO: Check google play service
        if (checkPlayServices()){
            if (isNetworkConnected()) {
                final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Thread splashThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this){
                            try {
                                wait(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
                splashThread.start();
            } else {
                DialogUtils.createAlert(mContext, null, "Error network"
                        , "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
            }
        }
    }
}
