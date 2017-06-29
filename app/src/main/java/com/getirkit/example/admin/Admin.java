package com.getirkit.example.admin;

import com.getirkit.example.activity.MainActivity;

import java.util.List;

/**
 * Created by 健太 on 2017/06/29.
 */

public class Admin extends GroupAdapter{
    //トリガー一覧を格納するList
    List<GroupAdapter> TList;

    //Listにトリガーを格納していく処理
    void setting(){
        TList.add(new GroupAdapter() {
            @Override
            void start() {

            }

            @Override
            void Transmission() {

            }
        });
    }

  //選択されたトリガーの監視スタート
    @Override
    void start() {
        //ループで一斉に実行、又はチェックリストでの管理
        //TList.get(0).start();
    }
//選択されたトリガーの赤外線送信
    @Override
    void Transmission() {

    }
}
