package com.example.devesh.attendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.devesh.attendance.Models.DatabaseTable;

/**
 * Created by devesh on 30/8/16.
 */
public class Database extends SQLiteOpenHelper {

    public static final String Database_Name = "Attendance";
    public static final int DB_Version = 1;
    private static Database myDatabase = null;

    public static SQLiteDatabase getReadable(Context ctx) {
        if (myDatabase == null) {
            myDatabase = new Database(ctx);
        }
        return myDatabase.getReadableDatabase();
    }

    public static SQLiteDatabase getWritable(Context ctx) {
        if (myDatabase == null) {
            myDatabase = new Database(ctx);
        }
        return myDatabase.getWritableDatabase();
    }

    public Database(Context context) {
        super(context, Database_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseTable.TABLE_CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
