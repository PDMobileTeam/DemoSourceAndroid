package com.phuocdai.demosourceandroid.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

/**
 * Created by phuocdai on 1/27/16.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener{
    private ProgressDialog mLoadingDialog;
    boolean doubleBackToExitPressedOnce = false;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setupView();
    }

    protected void setupView(){

    }

    @Override
    public void onClick(View v) {

    }

    public void showDialogLoading(){
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            return;
        }

        if (mLoadingDialog == null){
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage("Loading...");
            mLoadingDialog.setIndeterminate(true);
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setCanceledOnTouchOutside(false);
        }
        mLoadingDialog.show();;
    }

    public void dismissLoading(){
        if (mLoadingDialog != null){
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    public void setupActionBar(boolean showHomeAsUp){
        // Asking for the default ActionBar element that our platform supports.
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }

    public boolean isNetworkConnected(){
        Context context = this;
        boolean isNetworkConnected = false;
        if (context == null){
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null){
            final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null){
                isNetworkConnected = info.isConnectedOrConnecting();
            }
        }
        return isNetworkConnected;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please tap again to exit app.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }
}
