package com.getirkit.example.Settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.getirkit.example.R;

public class WifiSettingActivity extends AppCompatActivity {

    private BaseAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_setting);
    }
}
