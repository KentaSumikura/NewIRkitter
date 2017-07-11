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
//a

public class TriggerlistActivity extends Activity {


    private ImageButton imageButton;
    private TextView textImageButton, textButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextView の設定
        textImageButton = (TextView) findViewById(R.id.text_imagebutton);
        textButton = (TextView) findViewById(R.id.text_button);

        // イメージボタンを設定
        imageButton = (ImageButton)findViewById(R.id.image_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}