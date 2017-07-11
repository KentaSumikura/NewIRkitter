package com.getirkit.example.activity;

/**
 * Created by user on 2017/07/10.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.getirkit.example.R;
//

public class TriggerlistActivity extends Activity {


    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // イメージボタンを設定
        imageButton = (ImageButton)findViewById(R.id.image_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}