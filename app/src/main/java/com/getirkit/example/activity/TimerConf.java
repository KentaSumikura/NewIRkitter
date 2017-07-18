package com.getirkit.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.getirkit.example.R;

public class TimerConf extends AppCompatActivity {
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView conbr;
    TextView anthr;
    TextView date;
    EditText editTexthour;//時間入力させるとこ
    EditText editTextminute;//分入力させるとこ
    Button set;
    int sethour;
    int setminute;
    int nowhour;
    int nowminute;
    Thread thread = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_conf);

        thread = new Thread(thread);
        thread.start();

        Log.d("ああ", "onCreate()");

        textView = (TextView) findViewById(R.id.hour_text);
        textView1 = (TextView)findViewById(R.id.minute_text);

        editTexthour = (EditText) findViewById(R.id.hour);
        editTextminute = (EditText) findViewById(R.id.minute);
        Log.d("やまがみ","やまもと");
        set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // エディットテキストの時間を取得
                String setText = editTexthour.getText().toString();
                // 取得したテキストを hour text に張り付ける (時間)
                textView.setText(setText);
                // エディットテキストの分を取得
                String setText2 = editTextminute.getText().toString();
                // 取得したテキストを minuteText に張り付ける(分)
                textView.setText(setText2);

                sethour = new Integer(setText);//nowhourに入力させた時間をいれる

                setminute = new Integer(setText2);//nowhourに入力させた分をいれる

                Log.d("test",String.valueOf(sethour));

                Log.d("やまもと",String.valueOf(setminute));

                while (true) {

                    try {
                        Thread.sleep(10000);
                    }catch (InterruptedException e){
                    }

                    Log.d("test","のだ");
                    // 現在日時の取得
                    Time now = new Time(System.currentTimeMillis());
                    // 日時のフォーマットオブジェクト作成
                    final DateFormat formatter = new SimpleDateFormat("hh");
                    // now変数にいれた現在日時をフォーマット
                    final String nowText = formatter.format(now);
                    // 日時のフォーマットオブジェクト作成
                    final DateFormat formatter2 = new SimpleDateFormat("mm");
                    // now変数に入れた分をフォーマット
                    final String nowText2 = formatter2.format(now);
                    // 表示
                    TextView date = (TextView) findViewById(R.id.date);
                    date.setText(nowText + ":" + nowText2);

                    nowhour = new Integer(nowText);
                    nowminute = new Integer(nowText2);

                    Log.d("test",String.valueOf(nowhour));

                    Log.d("test111",String.valueOf(nowminute));

            /*
            if (sethour > 12) {
                sethour = sethour - 12;//24時間表記を12時間表に変更
                Log.d("ave","mario");
            }
            */

                    if (sethour == nowhour) {//設定時と現在時の比較

                        conbr = (TextView) findViewById(R.id.conbr);

                        Log.d("amaging","break");

                        conbr.setText("成功");

                        conbr.setText("時間おｋ");

                        if (setminute == nowminute) {//設定分と現在分の比較

                            //表示（実際は赤外線送信処理）
                            Log.d("おｋ","");

                            anthr = (TextView) findViewById(R.id.anthr);

                            anthr.setText("成功");
                            break;
                        } else {

                            Log.d("だめ","");

                            anthr = (TextView) findViewById(R.id.anthr);

                            anthr.setText("失敗");
                        }
                    }
                    Log.d("つのだ","");
                    //break;
                }

            }
        });//?


    }



    }

