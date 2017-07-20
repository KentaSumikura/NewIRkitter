package com.getirkit.example.activity;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.getirkit.example.activity.DBManager.IRkitDBManager;
import com.getirkit.example.activity.datatable.DTableINFRARED;
import com.getirkit.example.activity.datatable.DTablePHONETBL;
import com.getirkit.example.admin.Admin;

import java.util.ArrayList;

/**
 * Created by 博之 on 2017/07/11.
 */

public class CallReceiver extends PhoneStateListener {
    Context context;
    //db
    public CallReceiver(Context context) {
        this.context = context;
    }

    // 着信と通話どっちでも行けるように2つあるよ
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        Admin admin = new Admin();

        switch (state) {
            //すみくらここらへ
            // 着信処理部分
            case TelephonyManager.CALL_STATE_RINGING:
              //  Toast.makeText(context, "着信" + incomingNumber, Toast.LENGTH_LONG).show();
                //admin.CallTransmission();
                admin.Transmission(0);
                break;
            // 通話処理部分
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Toast.makeText(context, "通話" + incomingNumber, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
