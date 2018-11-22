package com.moai101.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.moai101.data.DiaryContract.DiaryEntry;
//データベースを作るための処理

public class DiaryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+DiaryEntry.TABLE_NAME+
            "("+ DiaryEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DiaryEntry.COLUMN_TITLE+ " TEXT DEFAULT UNKNOWN," +
            DiaryEntry.COLUMN_DATE+" TEXT, "+
            DiaryEntry.COLUMN_IMAGE_DATA +" BLOB, " +
            DiaryEntry.COLUMN_DESCRIPTION +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION1 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION2 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION3 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION4 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION5 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION6 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION7 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION8 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION9 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION10+" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION11 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION12 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION13 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION14 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION15 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION16 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION17 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION18 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION19 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION20 +" TEXT, " +
            DiaryEntry.COLUMN_DESCRIPTION21+" TEXT )";

    public DiaryDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
             db.execSQL(CREATE_TABLE);
    }
//データベースのアップグレードが必要な時に使う。
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}