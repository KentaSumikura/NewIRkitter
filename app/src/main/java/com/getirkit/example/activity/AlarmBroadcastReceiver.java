package com.getirkit.example.activity;

/**
 * Created by user on 2017/07/24.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;
import android.util.Log;

import com.getirkit.example.R;
import com.getirkit.example.activity.DBManager.IRkitDBManager;
import com.getirkit.example.activity.datatable.DTablePHONETBL;
import com.getirkit.example.activity.datatable.DTableTIME;
import com.getirkit.example.admin.Admin;

import java.util.ArrayList;

public class AlarmBroadcastReceiver extends BroadcastReceiver{

    Context context;

    @Override   // データを受信した
    public void onReceive(Context context, Intent intent) {
        Log.d("やまがみ1","やまもと1");
        this.context = context;

        Log.d("AlarmBroadcastReceiver","onReceive() pid=" + android.os.Process.myPid());

        int bid = intent.getIntExtra("intentId",0);

        Intent intent2 = new Intent(context, MainActivity.class);
        Log.d("やまがみ3","やまもと");
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, bid, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("時間です")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("TestAlarm "+bid)
                .setContentText("時間になりました")
                // 音、バイブレート、LEDで通知
                .setDefaults(Notification.DEFAULT_ALL)
                // 通知をタップした時にMainActivityを立ち上げる
                .setContentIntent(pendingIntent)
                .build();
        //db
        Admin admin = new Admin();
        IRkitDBManager mgr = new IRkitDBManager(context);

        ArrayList<DTableTIME> phoneilst  = new ArrayList<DTableTIME>();
        phoneilst = mgr.selectAllTIME();
        for (DTableTIME phonetbl: phoneilst
                ) {
            int po = (int) phonetbl.getREDID();

            admin.Transmission(po);
        }
        mgr.close();
        //ここまで追記テスト

        // 古い通知を削除
        notificationManager.cancelAll();
        // 通知
        notificationManager.notify(R.string.app_name, notification);
    }
}
