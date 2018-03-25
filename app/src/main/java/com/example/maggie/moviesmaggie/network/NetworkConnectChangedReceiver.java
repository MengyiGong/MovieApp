package com.example.maggie.moviesmaggie.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.maggie.moviesmaggie.MyApplication;

public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkReceiver";
    private MyApplication mApplication;

    public NetworkConnectChangedReceiver(MyApplication application) {
        mApplication = application;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                mApplication.setConnected(true);
                Log.i(TAG, "Internet Connected!");
            } else {
                mApplication.setConnected(false);
                Log.e(TAG, "No Internet Connection");
            }
        }
    }
}
