package com.mubangizi.sqlexamples;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;

public class SqlDbOpenHelper extends SQLiteOpenHelper {
    String schema;
    ArrayList<String> tables;

    public SqlDbOpenHelper(Context context, String name) {
        super(context, name, null, 1);
        String[] strings = context.getResources().getStringArray(R.array.app_db_tables);
        this.tables = (ArrayList<String>) Arrays.asList(strings);
        this.schema = context.getString(R.string.app_db_schema);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(schema);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        tables.forEach((table) -> database.execSQL("DROP TABLE IF EXISTS " + table + ";"));
        onCreate(database);
    }
}
