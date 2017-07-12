package com.getirkit.example.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.getirkit.example.R;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Created by 博之 on 2017/07/05.
 */

public class CallConf extends BroadcastReceiver {
    TelephonyManager manager;
    CallReceiver phoneStateListener;
    static boolean listener = false;


    @Override
    public void onReceive(Context context, Intent intent) {
        // PhoneReceiverインスタンス生成
        phoneStateListener = new CallReceiver(context);
        // TelephonyManagerインスタンス生成
        manager =((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE));

        if(!listener) {
            manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
            listener = true;
        }
    }
}

