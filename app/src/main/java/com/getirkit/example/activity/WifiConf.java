package com.getirkit.example.activity;

import android.content.ActivityNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.getirkit.example.R;
import com.getirkit.example.admin.Admin;

/**
 * Created by 健太 on 2017/07/05.
 */
public  class WifiConf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_conf);

        // ボタンを設定
        Button button = (Button)findViewById(R.id.button7);

        // リスナーをボタンに登録
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getwifissid();
            }
        });
    }

    public void getwifissid()
    {
        try {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo w_info = wifiManager.getConnectionInfo();
            Toast.makeText(this, w_info.getSSID(), Toast.LENGTH_LONG).show();
        } catch (ActivityNotFoundException e) {
            // エラー表示
            Toast.makeText(WifiConf.this,
                    "ActivityNotFoundException", Toast.LENGTH_LONG).show();
        }
    }

}

