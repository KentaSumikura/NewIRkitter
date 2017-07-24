package com.getirkit.example.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.getirkit.example.R;
import com.getirkit.example.activity.DBManager.IRkitDBManager;
import com.getirkit.example.activity.datatable.DTableINFRARED;

import java.util.ArrayList;

public class CallConf extends AppCompatActivity {

    // 各フィールドの設定
    CallReceiver phoneStateListener;
    TelephonyManager manager;
    int item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_conf);

        // PhoneReceiverインスタンスの生成
        phoneStateListener = new CallReceiver(this);
        // TelephonyManagerインスタンスの生成(Context.TELEPHONY_SERVICEを指定)
        manager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));

        //db
        IRkitDBManager dbwifi = new IRkitDBManager(getApplicationContext());
        ArrayList<DTableINFRARED> infrailst  = new ArrayList<DTableINFRARED>();
        infrailst = dbwifi.selectAllINFRARED();

        Button button2 = (Button)findViewById(R.id.settingbtn);

        //プルダウンメニュー
        Spinner spinner;
        ArrayList<String> spinnerItems = new ArrayList<>();

        for (DTableINFRARED infra: infrailst
                ) {

            spinnerItems.add(infra.getREDPATTERN());

        }


        spinner = (Spinner)findViewById(R.id.spinner);

        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,spinnerItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner に adapter をセット
        spinner.setAdapter(adapter);

        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                item = spinner.getSelectedItemPosition();

                //赤外線送信
                //Admin admin = new Admin();
                //admin.Transmission(item);

                // String TAG = "Wifi";
                // Log.d(TAG,""+item);

            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        // 設定終了ボタン
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IRkitDBManager dbwifi = new IRkitDBManager(getApplicationContext());

                //電話情報保存
                int posion = item;

                long flag = dbwifi.insertPHONETBL(posion);

                if(flag == 0) {
                    Toast toast = Toast.makeText(CallConf.this, "登録しました", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(CallConf.this, "既に登録されています", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
