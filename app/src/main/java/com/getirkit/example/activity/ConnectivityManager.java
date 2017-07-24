package com.getirkit.example.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.getirkit.example.activity.DBManager.IRkitDBManager;
import com.getirkit.example.activity.datatable.DTablePHONETBL;
import com.getirkit.example.activity.datatable.DTableWIFI;
import com.getirkit.example.admin.Admin;

import java.util.ArrayList;

import static android.content.Context.WIFI_SERVICE;
import static com.getirkit.example.activity.MainActivity.TAG;

/**
 * Created by owner on 2017/07/24.
 */

public class ConnectivityManager extends PhoneStateListener {

    private Context context;
    private Intent intent;
    //db
    public ConnectivityManager(Context context) {
        this.context = context;
    }

    @Override
    public void onDataConnectionStateChanged(int state) {
        //super.onDataConnectionStateChanged(state);
        switch (state) {
            case TelephonyManager.DATA_DISCONNECTED:

                break;

            case TelephonyManager.DATA_CONNECTED:

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
                                wifilst = mgr.selectWIFI(w_info.getSSID().replace("\"", ""));
                                for (DTableWIFI wifitbl: wifilst
                                        ) {
                                    int po = (int) wifitbl.getREDID();

                                    admin.Transmission(po);
                                }
                                mgr.close();
                            }
                        }
                    }
                }

                break;

        }
    }
}
