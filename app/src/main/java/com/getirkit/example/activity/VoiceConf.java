package com.getirkit.example.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.content.ActivityNotFoundException;
import android.text.SpannableStringBuilder;
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
import com.getirkit.example.activity.datatable.DTablePHONETBL;
import com.getirkit.example.activity.datatable.DTableVOICE;
import com.getirkit.example.admin.Admin;

import java.util.ArrayList;

public class VoiceConf extends AppCompatActivity {

    // リクエストを識別するための変数宣言。適当な数字でよい
    private static final int REQUEST_CODE = 0;

    public static final String TAG = "IRkitterDBOpenHelper";

    private Context context;
    IRkitDBManager dbwifi = new IRkitDBManager(getApplicationContext());
    int item;
    String Voice;
    Admin admin = new Admin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_conf);


        //dbセットアップ
        //IRkitDBManager dbwifi = new IRkitDBManager(getApplicationContext());
        ArrayList<DTableINFRARED> infrailst = new ArrayList<DTableINFRARED>();
        infrailst = dbwifi.selectAllINFRARED();
        //ボタンセットアップ
        Button button2 = (Button) findViewById(R.id.button2);
        Button button = (Button)findViewById(R.id.button);
        final EditText edit = (EditText)findViewById(R.id.editText);


        //プルダウンメニュー
        Spinner spinner;
        ArrayList<String> spinnerItems = new ArrayList<>();

        for (DTableINFRARED infra : infrailst
                ) {

            spinnerItems.add(infra.getREDPATTERN());

        }

        spinner = (Spinner) findViewById(R.id.spinner);

        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner に adapter をセット
        spinner.setAdapter(adapter);

        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Spinner spinner = (Spinner) parent;
                item = spinner.getSelectedItemPosition();

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

                //赤外線のポジション情報
                int posion = item;

                //long flag = dbwifi.insertVOICE(posion,Voice);

                //合言葉保存
                SpannableStringBuilder sb = (SpannableStringBuilder)edit.getText();
                Voice = sb.toString();

                dbwifi.insertVOICE(posion,Voice);

              //  if (flag == 0) {
                    Toast toast = Toast.makeText(VoiceConf.this, "登録しました", Toast.LENGTH_LONG);
                    toast.show();
               // } else {
                   // toast = Toast.makeText(VoiceConf.this, "既に登録されています", Toast.LENGTH_LONG);
                   // toast.show();
               // }
            }
        });



      // リスナーをボタンに登録
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voicestart();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // 音声認識結果のとき
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // 結果文字列リストを取得
            ArrayList<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            // 取得した文字列を結合
            String resultsString = "";
            resultsString += results.get(0);

            TextView textView = (TextView) findViewById(R.id.textView5);
            textView.setText(resultsString);

            ArrayList<DTableVOICE> voiceilst  = new ArrayList<DTableVOICE>();
            voiceilst = dbwifi.selectAllVOICE();
            for (DTableVOICE voicetbl: voiceilst
                    ) {
                if (resultsString == voicetbl.getVOICE()){

                    int po = (int) voicetbl.getREDID();
                    admin.Transmission(po);
                }

            }
            dbwifi.close();



        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    // タッチイベントが起きたら呼ばれる関数
    public boolean voicestart() {
        // 画面から指が離れるイベントの場合のみ実行

        try {
            // 音声認識プロンプトを立ち上げるインテント作成
            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            // 言語モデルをfree-form speech recognitionに設定
            // web search terms用のLANGUAGE_MODEL_WEB_SEARCHにすると検索画面になる
            intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            // プロンプトに表示する文字を設定
            intent.putExtra(
                    RecognizerIntent.EXTRA_PROMPT,
                    "話してください");
            // インテント発行
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // エラー表示
            Toast.makeText(VoiceConf.this,
                    "ActivityNotFoundException", Toast.LENGTH_LONG).show();
        }

        return true;
    }
}