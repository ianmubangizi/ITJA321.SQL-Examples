package com.mubangizi.sqlexamples.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Table {

    protected String table;
    private String[] columns;
    private SQLiteOpenHelper db;

    public Table(SQLiteOpenHelper database, String name, String... columns) {
        this.table = name;
        this.db = database;
        this.columns = columns;
    }

    /**
     * @return the row id on success else -1
     */
    public long insert(ContentValues values) {
        return db.getWritableDatabase().insert(table, null, values);
    }

    /**
     * @param where this param should look something like the example
     *              <code>
     *              "value = ?" or
     *              "value = ? AND other = ?" also
     *              "value = ? OR other = ? AND another = ?"
     *              <code/>
     * @param args  set the values for the where clause in order of use
     * @return the number of row that got deleted or zero if non
     */
    public int delete(String where, String... args) {
        return db.getWritableDatabase().delete(table, where, args);
    }

    /**
     * @param columns the names to be used when putting data
     * @param where   search for those that match <code>"value = ?" where ? is the arg value<code/>
     * @param args    to be used with the where param as the value
     */
    public Cursor select(String[] columns, String where, String... args) {
        return db.getWritableDatabase().query(table, columns, where, args, null, null, null);
    }

    /**
     * @see com.mubangizi.sqlexamples.data.Table#select(String[], String, String...)
     */
    public Cursor get(String where, String... args) {
        return select(columns, where, args);
    }
}
