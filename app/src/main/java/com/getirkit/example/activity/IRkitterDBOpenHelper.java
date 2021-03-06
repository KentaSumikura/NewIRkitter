package com.getirkit.example.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by owner on 2017/06/22.
 */

public class IRkitterDBOpenHelper extends SQLiteOpenHelper
{
    private static final String TAG = "IRkitterDBOpenHelper";

    static final String DATABASE_NAME = "irkitter.db";
    static final int DATABASE_VERSION = 3;

    public IRkitterDBOpenHelper (Context context)
    {
        //データベースファイル名とバージョンを指定しSQLiteOpenHelperクラスを初期化
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "IRkitterDBOpenHelperのコンストラクタが呼ばれました");
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        try {
            Log.d(TAG, "IRkitterDBOpenHelper.onCreateが呼ばれました");

            //infraredテーブルを生成
            database.execSQL("create table infrared("
                    + "redid integer primary key autoincrement,"
                    + "redpattern text not null);");

            //iconテーブルを生成
            database.execSQL("create table icon("
                    + "iconid integer primary key autoincrement,"
                    + "url text not null)");

            //orderテーブルを生成
            database.execSQL("create table order("
                    + "redid integer primary key ,"
                    + "ordername text not null,"
                    + "iconid integer,"
                    + "foreign key (redid) references infrared(redid),"
                    + "foreign key (iconid) references icon(iconid))");

            //voiceテーブルを生成
            database.execSQL("create table voice("
                    + "redid integer primary key ,"
                    + "voice text not null,"
                    + "foreign key (redid) references infrared(redid))");

            //wifiテーブルを生成
            database.execSQL("create table wifi("
                    + "redid integer primary key ,"
                    + "wifissid text not null,"
                    + "foreign key (redid) references infrared(redid))");

            //weatherテーブルを生成
            database.execSQL("create table weather("
                    + "redid integer primary key ,"
                    + "weather text not null,"
                    + "foreign key (redid) references infrared(redid))");

            //gpsテーブルを生成
            database.execSQL("create table gps("
                    + "redid integer primary key ,"
                    + "gps text not null,"
                    + "foreign key (redid) references infrared(redid))");

            //tempテーブルを生成
            database.execSQL("create table temp("
                    + "redid integer primary key ,"
                    + "temp integer not null,"
                    + "foreign key (redid) references infrared(redid))");

            //timeテーブルを生成
            database.execSQL("create table time("
                    + "redid integer primary key ,"
                    + "time text not null,"
                    + "foreign key (redid) references infrared(redid))");

            //angularテーブルを生成
            database.execSQL("create table angular("
                    + "redid integer primary key ,"
                    + "angular integer not null default 100,"
                    + "foreign key (redid) references infrared(redid))");

            //speedテーブルを生成
            database.execSQL("create table speed("
                    + "redid integer primary key ,"
                    + "speed integer not null default 100,"
                    + "foreign key (redid) references infrared(redid))");

            //accelerationテーブルを生成
            database.execSQL("create table acceleration("
                    + "redid integer primary key ,"
                    + "acceleration integer not null default 100,"
                    + "foreign key (redid) references infrared(redid))");

            //fingerprintテーブルを生成
            database.execSQL("create table fingerprint("
                    + "redid integer primary key ,"
                    + "fingerprint integer not null default 100,"
                    + "foreign key (redid) references infrared(redid))");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try
        {
            Log.d(TAG, "IRkitterDBOpenHelper.onUpgradeが呼ばれました");
            //それぞれのテーブルを再定義するために現在のテーブルを削除
            db.execSQL("drop table if exists infrared");
            db.execSQL("drop table if exists icon");
            db.execSQL("drop table if exists order");
            db.execSQL("drop table if exists voice");
            db.execSQL("drop table if exists wifi");
            db.execSQL("drop table if exists weather");
            db.execSQL("drop table if exists gps");
            db.execSQL("drop table if exists temp");
            db.execSQL("drop table if exists time");
            db.execSQL("drop table if exists angular");
            db.execSQL("drop table if exists speed");
            db.execSQL("drop table if exists acceleration");
            db.execSQL("drop table if exists fingerprint");
            onCreate(db);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
