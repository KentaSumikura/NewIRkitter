package com.getirkit.example.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.getirkit.example.R;

public class CallConf extends AppCompatActivity {

    // 各フィールドの設定
    CallReceiver phoneStateListener;
    TelephonyManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_conf);

        // PhoneReceiverインスタンスの生成
        phoneStateListener = new CallReceiver(this);
        // TelephonyManagerインスタンスの生成(Context.TELEPHONY_SERVICEを指定)
        manager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
