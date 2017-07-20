package com.getirkit.example.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by 博之 on 2017/07/12.
 */

public class CallStateReceiver  extends BroadcastReceiver {
    // 各フィールドの定義
    //TelephonyManager manager;
    //CallReceiver phoneStateListener;
    static boolean listener = false;

    // intent情報を処理する
    @Override
    public void onReceive(Context context, Intent intent) {
        // PhoneReceiverインスタンスの生成
        CallReceiver phoneStateListener = new CallReceiver(context);
        // TelephonyManagerインスタンスの生成
        TelephonyManager manager =((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE));

        if(!listener) {
            manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
            listener = true;
        }
    }
}

