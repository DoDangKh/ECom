package com.example.ecom;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_NAME="ECom";
    public static final int ver=1;
    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME,null,ver);
    }

    public void QueryData(String query){
        SQLiteDatabase database=getReadableDatabase();
        database.execSQL(query);
    }
    public Cursor GetData(String query){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(query,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "Create Table If Not Exists Products(Id Integer Primary Key AUTOINCREMENT,Name VARCHAR(50), Description VARCHAR(200),Price Integer)";
        db.execSQL(query);
        query="Insert into Products values(null,'Nike shoes','High quality Nike shoes',50)";
        db.execSQL(query);

//        query="Insert into Products values(null,'Puma shoes','High quality Puma shoes',60)";
//        db.execSQL(query);
        query="Insert into Products values(null,'adidas shoes','High quality adidas shoes',60)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
