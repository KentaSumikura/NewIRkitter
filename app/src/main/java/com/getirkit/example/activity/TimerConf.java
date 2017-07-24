package com.getirkit.example.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.widget.Toast;

import com.getirkit.example.R;

public class TimerConf extends AppCompatActivity {
    private static int bid2 = 2;
    private static int bid1 = 1;

    private Button button2, button3;
    private TextView textView;
    public EditText editTextmonth;
    public EditText editTextdate;
    public EditText editTexthour;//時間入力させるとこ
    public EditText editTextminute;//分入力させるとこ
    private int year, month, date, hour, minute, second, msecond;
    private int setText,setText2,setText3,setText4;

    private int sethour;
    private int setminute;
    private int setmonth;
    private int setdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_conf);

        //アラームの取り消し
        button2 = (Button) this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                // 5秒後に設定
                calendar.add(Calendar.SECOND, 5);

                Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                intent.putExtra("intentId", 1);
                // PendingIntentが同じ物の場合は上書きされてしまうので requestCode で区別する
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) TimerConf.this.getSystemService(ALARM_SERVICE);
                // 約10秒で 繰り返し
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10000, pending);

                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "ALARM 1", Toast.LENGTH_SHORT).show();

                // 無理やりですが、アプリを一旦終了します。この方法はバックグラウンドに移行させるための方便で推奨ではありません
                close();
            }
        });

        textView = (TextView) findViewById(R.id.text_view);
        // 協定世界時 (UTC)です適宜設定してください
        editTextmonth = (EditText) findViewById(R.id.month);
        editTextdate = (EditText) findViewById(R.id.date);
        editTexthour = (EditText) findViewById(R.id.hour);
        editTextminute = (EditText) findViewById(R.id.minute);


        // 日時を指定したアラーム
        button3 = (Button) this.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("やまがみ","やまもと");

                // エディットテキストの時間を取得
                setText = Integer.parseInt(editTexthour.getText().toString());
                // 取得したテキストを hour text に張り付ける (時間)
                textView.setText(String.valueOf(setText));
                // エディットテキストの分を取得
                setText2 = Integer.parseInt(editTextminute.getText().toString());
                // 取得したテキストを minuteText に張り付ける(分)
                textView.setText(String.valueOf(setText2));
                // エディットテキストの日付を取得
                setText3 = Integer.parseInt(editTextdate.getText().toString());
                // 取得したテキストを date text に張り付ける (時間)
                textView.setText(String.valueOf(setText3));
                // エディットテキストの時間を取得
                setText4 = Integer.parseInt(editTextmonth.getText().toString());
                // 取得したテキストを hour text に張り付ける (時間)
                textView.setText(String.valueOf(setText4));

                /*
                sethour = new Integer(setText);//nowhourに入力させた時間をいれる
                setminute = new Integer(setText2);//nowhourに入力させた分をいれる
                setdate= new Integer(setText3);
                setmonth = new Integer(setText4);
                */


                sethour = setText;//nowhourに入力させた時間をいれる
                setminute = setText2;//nowhourに入力させた分をいれる
                setdate= setText3;
                setmonth = setText4;

                year = 2017;
                month = setmonth - 1;
                date =setdate;
                hour = sethour;
                minute = setminute;
                second = 0;
                msecond = 0;

                Calendar calendar2 = Calendar.getInstance();
                // 過去の時間は即実行されます
                calendar2.set(Calendar.YEAR, year);
                calendar2.set(Calendar.MONTH, month);
                calendar2.set(Calendar.DATE, date);
                calendar2.set(Calendar.HOUR_OF_DAY, hour);
                calendar2.set(Calendar.MINUTE, minute );
                calendar2.set(Calendar.SECOND, second);
                calendar2.set(Calendar.MILLISECOND, msecond);

                Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                intent.putExtra("intentId", 2);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid2, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) TimerConf.this.getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pending);
                Toast.makeText(getApplicationContext(), "ALARM 2", Toast.LENGTH_SHORT).show();

                String setTime = "設定時間(UTC)："+year+"/"+(month + 1)+"/"+date+" "+hour+":"+minute+":"+second+"."+msecond;
                textView.setText(setTime);

            }
        });
    }

    private void close() {
        finish();
    }
}




