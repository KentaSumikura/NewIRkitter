package com.getirkit.example.activity;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by 博之 on 2017/07/11.
 */

public class CallReceiver extends PhoneStateListener {
    Context context;
    public CallReceiver(Context context) {
        this.context = context;
    }

    // 着信と通話どっちでも行けるように2つあるよ
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        switch (state) {
            //すみくらここらへ
            // 着信処理部分
            case TelephonyManager.CALL_STATE_RINGING:
                Toast.makeText(context, "着信" + incomingNumber, Toast.LENGTH_LONG).show();
                break;
            // 通話処理部分
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Toast.makeText(context, "通話" + incomingNumber, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
