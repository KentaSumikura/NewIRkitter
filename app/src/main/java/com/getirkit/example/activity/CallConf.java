package com.getirkit.example.activity;

import android.content.Context;
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

public class CallConf extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callconf_main);
        final TextView tvDescription = (TextView) findViewById(R.id.description);
        final Switch callSwitch = (Switch) findViewById(R.id.callSwitch);

        // スイッチのON・OFF
        callSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    // 電話の状況を検知するリスナーをセット
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    tm.listen(mPhoneStateListener,
                            PhoneStateListener.LISTEN_CALL_STATE);

                    tvDescription
                            .setText(getString(R.string.label_detect_callphone));
                    tvDescription.setVisibility(View.VISIBLE);
                } else {
                    tvDescription.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 電話の状態を受け取るリスナー.
     */
    PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        public void onCallStateChanged(int state, String number) {
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                // 着信を検知
                Toast.makeText(CallConf.this,
                        getString(R.string.label_detect_calling, number),
                        Toast.LENGTH_SHORT).show();
            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                // 通話を検知
                Toast.makeText(CallConf.this,
                        getString(R.string.label_detect_talking, number),
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
