package com.getirkit.example.activity.DBManager;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.getirkit.example.activity.datatable.DTableORDERTBL;
        import com.getirkit.example.activity.datatable.DTablePHONETBL;

        import java.util.ArrayList;

public class IRkitDBManager extends SQLiteOpenHelper {

    SQLiteDatabase sdb = null;
    private static final String TAG = "IRkitDBManager";

    public IRkitDBManager(Context context) {
        super(context,"irkitter" , null, 3);
        sdb = this.getWritableDatabase();
        Log.d(TAG, "IRkitDBManagerのコンストラクタが呼ばれました");
    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        if( arg1 == 2 && arg2 == 3 ) {
            Log.d(TAG, "IRkitterDBOpenHelper.onUpgradeが呼ばれました");
            //それぞれのテーブルを再定義するために現在のテーブルを削除
            arg0.execSQL("drop table if exists ordertbl");
            arg0.execSQL("drop table if exists voice");
            arg0.execSQL("drop table if exists wifi");
            arg0.execSQL("drop table if exists weather");
            arg0.execSQL("drop table if exists gps");
            arg0.execSQL("drop table if exists temp");
            arg0.execSQL("drop table if exists time");
            arg0.execSQL("drop table if exists angular");
            arg0.execSQL("drop table if exists speed");
            arg0.execSQL("drop table if exists acceleration");
            arg0.execSQL("drop table if exists fingerprint");
            arg0.execSQL("drop table if exists phonetbl");
            arg0.execSQL("drop table if exists infrared");
            arg0.execSQL("drop table if exists icon");
            onCreate(arg0);
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "IRkitDBManager.onCreateが呼ばれました");

        db.execSQL("create table infrared("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,redpattern TEXT"        //赤外線命令の名前です(例：テレビ電源など)
                + ");");

        db.execSQL("create table icon("
                + "     iconid INTEGER PRIMARY KEY "
                + "    ,url TEXT"
                + ");");

        db.execSQL("create table ordertbl("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,ordername TEXT"
                + "    ,iconid INTEGER"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + "    ,FOREIGN KEY (iconid) REFERENCES icon(iconid)"
                + ");");

        db.execSQL("create table voice("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,voice TEXT"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table wifi("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,wifissid TEXT"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table weather("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,weather TEXT"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table gps("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,gps TEXT"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table temp("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,temp INTEGER"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table time("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,time INTEGER"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table angular("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,angular INTEGER"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table speed("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,speed INTEGER"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table acceleration("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,acceleration INTEGER"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table fingerprint("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,fingerprint INTEGER"
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

        db.execSQL("create table phonetbl("
                + "     redid INTEGER PRIMARY KEY "
                + "    ,onoff INTEGER DEFAULT 0"
                + "    ,FOREIGN KEY (redid) REFERENCES infrared(redid)"
                + ");");

    }
    //************** infrared�p�֐� ***************
    public void insertINFRARED(
            String redpattern
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redpattern", redpattern);

            //�e�[�u���ɑ}��
            sdb.insert("infrared", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void ALLDeleteINFRARED(
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            sdb.delete("infrared", null, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableINFRARED> selectAllINFRARED(){
        ArrayList<com.getirkit.example.activity.datatable.DTableINFRARED> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableINFRARED>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM infrared;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableINFRARED dt = new com.getirkit.example.activity.datatable.DTableINFRARED();
            dt.setREDID(c.getLong(0));
            dt.setREDPATTERN(c.getString(1));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** icon�p�֐� ***************
    public void insertICON(
            String url
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("url", url);

            //�e�[�u���ɑ}��
            sdb.insert("icon", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableICON> selectAllICON(){
        ArrayList<com.getirkit.example.activity.datatable.DTableICON> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableICON>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM icon;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableICON dt = new com.getirkit.example.activity.datatable.DTableICON();
            dt.setICONID(c.getLong(0));
            dt.setURL(c.getString(1));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** ordertbl�p�֐� ***************
    public void insertORDERTBL(
            long redid
            ,String ordername
            ,long iconid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("ordername", ordername);
            cv.put("iconid", iconid);

            //�e�[�u���ɑ}��
            sdb.insert("ordertbl", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<DTableORDERTBL> selectAllORDER(){
        ArrayList<DTableORDERTBL> lst = new ArrayList<DTableORDERTBL>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM ordertbl;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            DTableORDERTBL dt = new DTableORDERTBL();
            dt.setREDID(c.getLong(0));
            dt.setORDERNAME(c.getString(1));
            dt.setICONID(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** voice�p�֐� ***************
    public void insertVOICE(
            long redid
            ,String voice
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("voice", voice);

            //�e�[�u���ɑ}��
            sdb.insert("voice", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateVOICEAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("voice", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateVOICEAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("voice", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateVOICEon(
        long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("voice", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateVOICEoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("voice", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableVOICE> selectAllVOICE(){
        ArrayList<com.getirkit.example.activity.datatable.DTableVOICE> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableVOICE>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM voice;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableVOICE dt = new com.getirkit.example.activity.datatable.DTableVOICE();
            dt.setREDID(c.getLong(0));
            dt.setVOICE(c.getString(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** wifi�p�֐� ***************
    public void insertWIFI(
            long redid
            ,String wifissid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("wifissid", wifissid);

            //�e�[�u���ɑ}��
            sdb.insert("wifi", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWIFIAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("wifi", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWIFIAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("wifi", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWIFIon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("wifi", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWIFIoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("wifi", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    //************** wifi�p�֐� ***************
    public void AlldeleteWIFI(
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            sdb.delete("wifi", null, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableWIFI> selectAllWIFI(){
        ArrayList<com.getirkit.example.activity.datatable.DTableWIFI> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableWIFI>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM wifi;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableWIFI dt = new com.getirkit.example.activity.datatable.DTableWIFI();
            dt.setREDID(c.getLong(0));
            dt.setWIFISSID(c.getString(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** weather�p�֐� ***************
    public void insertWEATHER(
            long redid
            ,String weather
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("weather", weather);

            //�e�[�u���ɑ}��
            sdb.insert("weather", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWEATHERAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("weather", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWEATHERAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("weather", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWEATHERon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("weather", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateWEATHERoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("weather", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableWEATHER> selectAllWEATHER(){
        ArrayList<com.getirkit.example.activity.datatable.DTableWEATHER> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableWEATHER>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM weather;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableWEATHER dt = new com.getirkit.example.activity.datatable.DTableWEATHER();
            dt.setREDID(c.getLong(0));
            dt.setWEATHER(c.getString(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** gps�p�֐� ***************
    public void insertGPS(
            long redid
            ,String gps
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("gps", gps);

            //�e�[�u���ɑ}��
            sdb.insert("gps", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateGPSAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("gps", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateGPSAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("gps", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateGPSon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("gps", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateGPSoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("gps", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableGPS> selectAllGPS(){
        ArrayList<com.getirkit.example.activity.datatable.DTableGPS> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableGPS>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM gps;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableGPS dt = new com.getirkit.example.activity.datatable.DTableGPS();
            dt.setREDID(c.getLong(0));
            dt.setGPS(c.getString(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** temp�p�֐� ***************
    public void insertTEMP(
            long redid
            ,long temp
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("temp", temp);

            //�e�[�u���ɑ}��
            sdb.insert("temp", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTEMPAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("temp", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTEMPAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("temp", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTEMPon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("temp", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTEMPoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("temp", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableTEMP> selectAllTEMP(){
        ArrayList<com.getirkit.example.activity.datatable.DTableTEMP> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableTEMP>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM temp;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableTEMP dt = new com.getirkit.example.activity.datatable.DTableTEMP();
            dt.setREDID(c.getLong(0));
            dt.setTEMP(c.getLong(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** time�p�֐� ***************
    public void insertTIME(
            long redid
            ,long time
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("time", time);

            //�e�[�u���ɑ}��
            sdb.insert("time", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTIMEAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("time", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTIMEAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("time", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTIMEon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("time", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateTIMEoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("time", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableTIME> selectAllTIME(){
        ArrayList<com.getirkit.example.activity.datatable.DTableTIME> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableTIME>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM time;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableTIME dt = new com.getirkit.example.activity.datatable.DTableTIME();
            dt.setREDID(c.getLong(0));
            dt.setTIME(c.getLong(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** angular�p�֐� ***************
    public void insertANGULAR(
            long redid
            ,long angular
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("angular", angular);

            //�e�[�u���ɑ}��
            sdb.insert("angular", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateANGULARAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("angular", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateANGULARAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("angular", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateANGULARon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("angular", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateANGULARoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("angular", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableANGULAR> selectAllANGULAR(){
        ArrayList<com.getirkit.example.activity.datatable.DTableANGULAR> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableANGULAR>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM angular;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableANGULAR dt = new com.getirkit.example.activity.datatable.DTableANGULAR();
            dt.setREDID(c.getLong(0));
            dt.setANGULAR(c.getLong(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** speed�p�֐� ***************
    public void insertSPEED(
            long redid
            ,long speed
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("speed", speed);

            //�e�[�u���ɑ}��
            sdb.insert("speed", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateSPEEDAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("speed", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateSPEEDAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("speed", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateSPEEDon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("speed", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateSPEEDoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("speed", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableSPEED> selectAllSPEED(){
        ArrayList<com.getirkit.example.activity.datatable.DTableSPEED> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableSPEED>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM speed;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableSPEED dt = new com.getirkit.example.activity.datatable.DTableSPEED();
            dt.setREDID(c.getLong(0));
            dt.setSPEED(c.getLong(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** acceleration�p�֐� ***************
    public void insertACCELERATION(
            long redid
            ,long acceleration
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("acceleration", acceleration);

            //�e�[�u���ɑ}��
            sdb.insert("acceleration", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateACCELERATIONAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("acceleration", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateACCELERATIONAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("acceleration", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateACCELERATIONon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("acceleration", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateACCELERATIONoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("acceleration", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableACCELERATION> selectAllACCELERATION(){
        ArrayList<com.getirkit.example.activity.datatable.DTableACCELERATION> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableACCELERATION>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM acceleration;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableACCELERATION dt = new com.getirkit.example.activity.datatable.DTableACCELERATION();
            dt.setREDID(c.getLong(0));
            dt.setACCELERATION(c.getLong(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }
    //************** fingerprint�p�֐� ***************
    public void insertFINGERPRINT(
            long redid
            ,long fingerprint
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            cv.put("fingerprint", fingerprint);

            //�e�[�u���ɑ}��
            sdb.insert("fingerprint", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateFINGERPRINTAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("fingerprint", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateFINGERPRINTAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("fingerprint", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateFINGERPRINTon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("fingerprint", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updateFINGERPRINToff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("fingerprint", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTableFINGERPRINT> selectAllFINGERPRINT(){
        ArrayList<com.getirkit.example.activity.datatable.DTableFINGERPRINT> lst = new ArrayList<com.getirkit.example.activity.datatable.DTableFINGERPRINT>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM fingerprint;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTableFINGERPRINT dt = new com.getirkit.example.activity.datatable.DTableFINGERPRINT();
            dt.setREDID(c.getLong(0));
            dt.setFINGERPRINT(c.getLong(1));
            dt.setONOFF(c.getLong(2));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }

    //************** phonetbl�p�֐� ***************
    public void insertPHONETBL(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);

            //�e�[�u���ɑ}��
            sdb.insert("phonetbl", "", cv);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updatePHONETBLAlloff(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("phonetbl", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updatePHONETBLAllon(

    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("phonetbl", null, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updatePHONETBLon(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+1;

            //�e�[�u���ɑ}��
            sdb.update("phonetbl", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public void updatePHONETBLoff(
            long redid
    ){
        try{
            //�g�����U�N�V�����J�n
            sdb.beginTransaction();

            //�}���������e���ݒ�
            ContentValues cv = new ContentValues();
            cv.put("redid", redid);
            String whereClause = "onoff = "+0;

            //�e�[�u���ɑ}��
            sdb.update("phonetbl", cv, whereClause, null);

            //�g�����U�N�V��������
            sdb.setTransactionSuccessful();

        }finally{
            //�g�����U�N�V�����I��
            sdb.endTransaction();
        }
    }

    public ArrayList<com.getirkit.example.activity.datatable.DTablePHONETBL> selectAllPHONETBL(){
        ArrayList<com.getirkit.example.activity.datatable.DTablePHONETBL> lst = new ArrayList<DTablePHONETBL>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT *");
        sql.append(" FROM phonetbl;");
        Cursor c = sdb.rawQuery(sql.toString(), null);
        boolean isEof = c.moveToFirst();
        while (isEof) {
            com.getirkit.example.activity.datatable.DTablePHONETBL dt = new com.getirkit.example.activity.datatable.DTablePHONETBL();
            dt.setREDID(c.getLong(0));
            dt.setONOFF(c.getLong(1));
            lst.add(dt);
            isEof = c.moveToNext();
        }
        return lst;
    }

}

