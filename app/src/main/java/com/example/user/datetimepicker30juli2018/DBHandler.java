package com.example.user.datetimepicker30juli2018;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 7/30/2018.
 */

public class DBHandler extends SQLiteOpenHelper{
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Datetime.db", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE msDateTime("+
                "Date TEXT, Time TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS msDateTime";
        sqLiteDatabase.execSQL(query);

        onCreate(sqLiteDatabase);
    }

}
