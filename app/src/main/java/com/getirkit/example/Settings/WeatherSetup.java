package com.getirkit.example.Settings;

/**
 * Created by user on 2017/07/10.
 */

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;
import com.getirkit.example.R;
import com.getirkit.example.activity.AppController;
import com.getirkit.example.activity.MainActivity;

public class WeatherSetup extends AppCompatActivity{
    // JSONデータ取得URL
    private String URL_API = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";
    private  SharedPreferences data;

    //  Volleyでリクエスト時に設定するタグ名、キャンセル時に利用 クラス名をタグ指定
    private static final Object TAG_REQUEST_QUEUE = MainActivity.class.getName();

    // ログ出力用のタグ
    private static final String TAG = MainActivity.class.getSimpleName();

    // Volleyへ渡すタグ
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_main.xml にUIコンポーネントを配置する
        setContentView(R.layout.activity_main);

        // text_view1： activity_main.xml の TextView の id
        TextView textView = (TextView) findViewById(R.id.text_view);

        // テキストを設定
        textView.setText("地域を選択してください");

        data = getSharedPreferences("DataStore",MODE_PRIVATE);

        String city = data.getString("DATA","Nothing");
        Log.d("eee",city);
        if(!city.equals("Nothing")){

            request(city);

        }else {


        /*
         * 表示
         */
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            // ArrayAdapter を、string-array とデフォルトのレイアウトを引数にして生成
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list, android.R.layout.simple_spinner_item);
            // 選択肢が表示された時に使用するレイアウトを指定
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // スピナーにアダプターを設定
            spinner.setAdapter(adapter);

        /*
         * イベントリスナー
         */
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.d(TAG, "onItemSelected");
                    // スピナー要素の文字列を取得
                    String selectedItemString = (String) parent.getItemAtPosition(position);

                    // 選択した要素で TextView を書き換え
                    TextView TextView = (TextView) findViewById(R.id.text_view1);
                    TextView.setText(selectedItemString);

                    // リクエスト処理
                    request(selectedItemString);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.d(TAG, "onNothingSelected");
                    Log.d(TAG, "AdapterView toString: " + parent.toString());
                    Toast.makeText(WeatherSetup.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
                }
            });

            Log.e(TAG, "onCreate");

        }

    }
    // リクエスト処理
    private void request(String selectedItemString) {

        switch (selectedItemString){
            case "福岡県":
                URL_API= URL_API +"400010";
                break;
            case "佐賀県":
                URL_API= URL_API +"410010";
                break;
            case "熊本県":
                URL_API= URL_API +"430010";
                break;
            case "長崎県":
                URL_API= URL_API +"420010";
                break;
            case "宮崎県":
                URL_API= URL_API +"450010";
                break;
            case "大分県":
                URL_API= URL_API +"440010";
                break;
            case "鹿児島県":
                URL_API= URL_API +"460010";
                break;
        }

        if(!selectedItemString.equals("選択してください")) {
            test2(selectedItemString);
        }

        // ロードダイアログ表示
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL_API, null,
                new Response.Listener<JSONObject>() {
                    // レスポンス受信のリスナー
                    @Override
                    public void onResponse(JSONObject response) {
                        // ログ出力
                        Log.d(TAG, "onResponse: " + response.toString());

                        // ロードダイアログ終了
                        pDialog.hide();

                        try {

                            // 天気予報の予報日毎の配列を取得
                            JSONArray forecasts = response.getJSONArray("forecasts");
                            for (int i = 0; i < 1; i++) {
                                JSONObject forecast = forecasts.getJSONObject(i);
                                // 日付を取得
                                String date = forecast.getString("date");
                                // 天気を取得
                                String telop = forecast.getString("telop");
                                // 温度を取得
                                String temperature = forecast.getString("temperature");
                                String str = temperature;
                                str = str.replaceAll("min", "最低気温");
                                str = str.replaceAll("max", "最高気温");
                                str = str.replaceAll("celsius", "摂氏");
                                str = str.replaceAll("fahrenheit", "華氏");
                                str = str.replaceAll("null", "なし");
                                str = str.replaceAll("\\{", "");
                                str = str.replaceAll("\\}", "");
                                str = str.replaceAll("\"", "");
                                str = str.replaceAll(":", "");
                                //地区名を取得
                                String title = response.getString("title");

                                //ダイアログ表示メソッド呼び出し
                                test(title,str,date,telop);
                            }

                            URL_API = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";

                        } catch (JSONException e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    // リクエストエラーのリスナー
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // ロードダイアログ終了
                        pDialog.hide();

                        // エラー処理
                        Log.d(TAG, "Error: " + error.getMessage());

                        if (error instanceof NetworkError) {
                        } else if (error instanceof ServerError) {
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                        } else if (error instanceof TimeoutError) {
                        }
                    }

                }
        );

        // シングルトンクラスで実行
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    //ダイアログ表示
    private void test(String title,String str,String date,String telop){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(title + "\n日付　:" + date + "\n天気　:" + telop + "\n気温　:" + str)
                .setPositiveButton("エアコンを予約する", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // ボタンをクリックしたときの動作

                    }
                });
        builder.show();

    }

    //選択された要素をローカルストレージに保存
    private void test2(String selectedItemString){

        SharedPreferences.Editor editor = data.edit();

        editor.putString("city",selectedItemString);

        editor.commit();

        String test = data.getString("city","");
        Log.d("test",test);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
