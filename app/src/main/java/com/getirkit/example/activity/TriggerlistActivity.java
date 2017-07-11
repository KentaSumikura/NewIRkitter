package com.getirkit.example.activity;

/**
 * Created by user on 2017/07/10.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.getirkit.example.R;
//

public class TriggerlistActivity extends Activity {


    private ImageButton TimerButton;
    private ImageButton CallingButton;
    private ImageButton WeatherButton;
    private ImageButton VoiceButton;
    private ImageButton WifiButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // タイマー画面に遷移させるボタン
        TimerButton = (ImageButton)findViewById(R.id.Timer_button);
        TimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CallingButton = (ImageButton)findViewById(R.id.Calling_button);
        CallingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        WeatherButton = (ImageButton)findViewById(R.id.Weather_button);
        WeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        VoiceButton = (ImageButton)findViewById(R.id.Voice_button);
        VoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        WifiButton = (ImageButton)findViewById(R.id.Wifi_button);
        WifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}