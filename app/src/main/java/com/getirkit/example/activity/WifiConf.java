package com.getirkit.example.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.getirkit.example.R;
import com.getirkit.example.activity.DBManager.IRkitDBManager;
import com.getirkit.example.activity.datatable.DTableINFRARED;
import com.getirkit.example.admin.Admin;

import java.util.ArrayList;

/**
 * Created by 健太 on 2017/07/05.
 */
public class WifiConf extends AppCompatActivity {

    // 各フィールドの設定
    CallReceiver phoneStateListener;
    TelephonyManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_conf);
        //db
        IRkitDBManager dbwifi = new IRkitDBManager(getApplicationContext());
        ArrayList<DTableINFRARED> wifilst  = new ArrayList<DTableINFRARED>();
        wifilst = dbwifi.selectAllINFRARED();
        //プルダウンメニュー
        Spinner spinner;
        ArrayList<String> spinnerItems = new ArrayList<>();
        //ArrayList<ArrayList<String>> spinnerItems = new ArrayList<ArrayList<String>>();

        for (DTableINFRARED infra: wifilst
                ) {
            //subItems.add(""+infra.getREDID()+infra.getREDPATTERN());
            //subItems.add(""+infra.getREDID());
            //spinnerItems.add(subItems);
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
                int item = spinner.getSelectedItemPosition();

                //赤外線送信
                Admin admin = new Admin();
                admin.Transmission(item);

                String TAG = "Wifi";
                Log.d(TAG,""+item);

            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });






        // PhoneReceiverインスタンスの生成
        phoneStateListener = new CallReceiver(this);
        // TelephonyManagerインスタンスの生成(Context.TELEPHONY_SERVICEを指定)
        manager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));


        final Admin admin = new Admin();


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
            //MainActivity main = new MainActivity();
            Admin admin = new Admin();
            String fr = "foreststarwars";
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo w_info = wifiManager.getConnectionInfo();
            EditText editText1 = (EditText)findViewById(R.id.editText);
            editText1.setText(w_info.getSSID().replace("\"", ""));
            String wifi = w_info.getSSID();
            if (fr.equals(w_info.getSSID().replace("\"", ""))){
                //main.onSelectSignalActionSend();
               // admin.Transmission();
            }else{
                editText1.setText("dame");
            }
            //Toast.makeText(this, w_info.getSSID(), Toast.LENGTH_LONG).show();
        } catch (ActivityNotFoundException e) {
            // エラー表示
            Toast.makeText(WifiConf.this,
                    "ActivityNotFoundException", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

}

