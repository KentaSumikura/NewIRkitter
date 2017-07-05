package com.getirkit.example.activity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        getwifissid();
    }

    public void getwifissid()
    {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo w_info = wifiManager.getConnectionInfo();
        Toast.makeText(this, w_info.getSSID(), Toast.LENGTH_LONG).show();
    }

}

