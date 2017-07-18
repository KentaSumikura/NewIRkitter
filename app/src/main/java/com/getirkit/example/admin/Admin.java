package com.getirkit.example.admin;

import com.getirkit.example.activity.CallConf;
import com.getirkit.example.activity.MainActivity;
import com.getirkit.example.activity.VoiceConf;
import com.getirkit.example.activity.WifiConf;

import java.util.List;

/**
 * Created by 健太 on 2017/06/29.
 */

public class Admin {
    MainActivity main = new MainActivity();
    WifiConf wifiConf = new WifiConf();
    VoiceConf voiceConf = new VoiceConf();
    CallConf callConf = new CallConf();
    //コンストラクタ
    public Admin(){

    }

  //選択されたトリガーの監視スタート
    public void start() {
        //一斉に実行（監視）
        wifiConf.getwifissid();
        voiceConf.voicestart();

    }




//選択されたトリガーの赤外線送信
   public void Transmission() {
            main.onSelectSignalActionSend();
    }
}
