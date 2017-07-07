package com.getirkit.example.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.getirkit.example.R;
import com.getirkit.example.admin.Admin;

import java.util.ArrayList;

public class VoiceConf extends AppCompatActivity {

    // リクエストを識別するための変数宣言。適当な数字でよい
    private static final int REQUEST_CODE = 0;

    public static final String TAG = "IRkitterDBOpenHelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_conf);

        // ボタンを設定
        Button button = (Button)findViewById(R.id.button);
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

            //IRkitterDBOpenHelperを生成
            IRkitterDBOpenHelper helper = new IRkitterDBOpenHelper(this);
            //書き込み可能なSQLiteDatabaseインスタンスを取得
            SQLiteDatabase db = helper.getWritableDatabase();

            //追加するデータを格納するContentValuesを生成
            ContentValues values = new ContentValues();
            //values.put(voice.redid, infraredid);              //irkitと登録する赤外線のidがないと実験できない
            //values.put(voice.voice, resultsString);
            //戻り値は生成されたデータの_IDが返却される
            //long id = db.insert(voice, null, values);
            //Log.d(TAG, "insert data:" + id);

            // トーストを使って結果表示
            //Toast.makeText(this, resultsString, Toast.LENGTH_LONG).show();

            TextView textView = (TextView) findViewById(R.id.textView5);
            textView.setText(resultsString);
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