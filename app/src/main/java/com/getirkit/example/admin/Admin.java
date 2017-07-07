package com.getirkit.example.admin;

import com.getirkit.example.activity.MainActivity;
import com.getirkit.example.activity.WifiConf;

import java.util.List;

/**
 * Created by 健太 on 2017/06/29.
 */

public class Admin {
    MainActivity main = new MainActivity();
    WifiConf wifiConf = new WifiConf();

    //コンストラクタ
    public Admin(){

    }

  //選択されたトリガーの監視スタート
    public void start() {
        //wifiConf.getwifissid();
        //ループで一斉に実行、又はチェックリストでの管理
        //VoiceConf.start();
    }
//選択されたトリガーの赤外線送信
   public void Transmission() {
            main.onSelectSignalActionSend();
    }
}
