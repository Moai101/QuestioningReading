package com.moai101.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ishita sharma on 7/19/2017.
 */

public final class DiaryContract {

//    認証局の設計。通常androidパッケージ名
    public static final String CONTENT_AUTHORITY = "com.moai101";
//引数uriStringからURIオブジェクトを生成する。。BASE_CONTENT_URIはdiaryテーブルやuserテーブルなどの元となるuri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ CONTENT_AUTHORITY );
    //コンテンツURIを作成するためにパス構造を設計
    public static final String PATH_DIARY = "diary";

    public static final String USER ="user";
    public static final String IMAGE ="image";
    private DiaryContract(){

    }

    public static final class DiaryEntry implements BaseColumns{

//        取り柄あえずはデータベースを扱う時はこの３つの島がいると覚えとく。

        public static final Uri CONTENT_URI= Uri.withAppendedPath(BASE_CONTENT_URI,PATH_DIARY);
        public static final String TABLE_NAME= "diary";

        //mime type for content uris
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+ "/" + CONTENT_AUTHORITY+ "/" +PATH_DIARY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +"/"+ CONTENT_AUTHORITY +"/"+ PATH_DIARY;

        //particulars for table
        public static final String _ID= BaseColumns._ID;
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE ="date";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DESCRIPTION1 = "description1";
        public static final String COLUMN_DESCRIPTION2 = "description2";
        public static final String COLUMN_DESCRIPTION3 = "description3";
        public static final String COLUMN_DESCRIPTION4 = "description4";
        public static final String COLUMN_DESCRIPTION5 = "description5";
        public static final String COLUMN_DESCRIPTION6 = "description6";
        public static final String COLUMN_DESCRIPTION7 = "description7";
        public static final String COLUMN_DESCRIPTION8 = "description8";
        public static final String COLUMN_DESCRIPTION9 = "description9";
        public static final String COLUMN_DESCRIPTION10 = "description10";
        public static final String COLUMN_DESCRIPTION11 = "description11";
        public static final String COLUMN_DESCRIPTION12 = "description12";
        public static final String COLUMN_DESCRIPTION13 = "description13";
        public static final String COLUMN_DESCRIPTION14 = "description14";
        public static final String COLUMN_DESCRIPTION15 = "description15";
        public static final String COLUMN_DESCRIPTION16 = "description16";
        public static final String COLUMN_DESCRIPTION17 = "description17";
        public static final String COLUMN_DESCRIPTION18 = "description18";
        public static final String COLUMN_DESCRIPTION19 = "description19";
        public static final String COLUMN_DESCRIPTION20 = "description20";
        public static final String COLUMN_DESCRIPTION21 = "description21";




        public static final Uri USER_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,USER);
        public static final String USER_TABLE_NAME ="user";

        public static final String USER_LIST_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE +"/"+ CONTENT_AUTHORITY+"/"+USER;
        public static final String USER_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +"/"+ CONTENT_AUTHORITY +"/"+ USER;


        public static final String _USER_ID= BaseColumns._ID;
        public static final String USER_COLUMN_NAME = "title";
        public static final String USER_COLUMN_EMAIL ="date";
        public static final String USER_COLUMN_NOTES = "description";



        public static final Uri IMAGE_URI =Uri.withAppendedPath(BASE_CONTENT_URI,IMAGE);
        public static final String IMAGE_TABLE_NAME="image";

        public static final String IMAGE_LIST_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE +"/"+ CONTENT_AUTHORITY+"/"+IMAGE;
        public static final String IMAGE_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +"/"+ CONTENT_AUTHORITY +"/"+ IMAGE;


        public static final String _IMAGE_ID= BaseColumns._ID;
        public static final String COLUMN_USER_IMAGE_DATA = "image_data";

    }
}
