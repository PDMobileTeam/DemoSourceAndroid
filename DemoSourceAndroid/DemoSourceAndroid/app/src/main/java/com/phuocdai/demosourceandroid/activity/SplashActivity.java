package com.phuocdai.demosourceandroid.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.phuocdai.demosourceandroid.R;

public class SplashActivity extends BaseActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

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
        //TODO: checkPlayService
        // Check device for Play Services APK.
        if (checkPlayServices()){
            startSplash();
        }
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
        final Intent intent = new Intent(SplashActivity.this,
                MainActivity.class);
        Thread splashThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        intent.setClass(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });
        splashThread.start();
    }
}
