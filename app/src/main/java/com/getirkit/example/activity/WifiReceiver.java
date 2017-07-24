package com.getirkit.example.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.getirkit.example.activity.DBManager.IRkitDBManager;
import com.getirkit.example.activity.datatable.DTableWIFI;
import com.getirkit.example.admin.Admin;

import java.util.ArrayList;

import static android.content.Context.WIFI_SERVICE;
import static com.getirkit.example.activity.AppController.TAG;

/**
 * Created by owner on 2017/07/21.
 */

public class WifiReceiver extends BroadcastReceiver {

    static boolean listener = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"きてるよ");
        /*ConnectivityManager phoneStateListener = new ConnectivityManager(context);
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        if(!listener) {
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
            listener = true;
        }*/
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                NetworkInfo info = extras
                        .getParcelable(WifiManager.EXTRA_NETWORK_INFO);
                if (info != null) {
                    if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                        Log.d(TAG, "レシーバーが呼び出されたよ");
                        // サービス呼び出し
                        /*context.startService(new Intent(context,
                                ほにゃららService.class));*/
                        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
                        WifiInfo w_info = wifiManager.getConnectionInfo();
                        String wifi = w_info.getSSID();
                        Admin admin = new Admin();
                        IRkitDBManager mgr = new IRkitDBManager(context);
                        ArrayList<DTableWIFI> wifilst  = new ArrayList<DTableWIFI>();
                        wifilst = mgr.selectWIFI(w_info.getSSID());
                        for (DTableWIFI wifitbl: wifilst
                                ) {
                            int po = (int) wifitbl.getREDID();

                            admin.Transmission(po);
                        }
                        listener = true;
                        mgr.close();
                    }
                }
            }
        }

    }
}
