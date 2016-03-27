package com.newsfeed.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.newsfeed.Constants;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CONTENT_AUTHORITY = "com.newsfeed";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String DATABASE_NAME = "com.newsfeed.database.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PaginationTable.Requests.CREATE_REQUEST);
        db.execSQL(NewsFeedTable.Requests.CREATE_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NewsFeedTable.Requests.DROP_REQUEST);
        db.execSQL(PaginationTable.Requests.DROP_REQUEST);
        onCreate(db);
    }
}
