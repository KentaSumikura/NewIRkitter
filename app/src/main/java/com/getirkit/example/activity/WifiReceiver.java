package com.getirkit.example.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import static com.getirkit.example.activity.AppController.TAG;

/**
 * Created by owner on 2017/07/21.
 */

public class WifiReceiver extends BroadcastReceiver {

    static boolean listener = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager phoneStateListener = new ConnectivityManager(context);
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        if(!listener) {
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
            listener = true;
        }

    }
}
