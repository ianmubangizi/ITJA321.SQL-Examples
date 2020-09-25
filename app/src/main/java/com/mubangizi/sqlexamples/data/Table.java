package com.mubangizi.sqlexamples.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Table {

    protected String table;
    private SQLiteOpenHelper db;

    public Table(SQLiteOpenHelper database, String name){
        this.db = database;
        this.table = name;
    }

    /** @return the row id on success else -1 */
    public long insert(ContentValues values){
        return db.getWritableDatabase().insert(table, null, values);
    }

    /**
     * @param where this param should look something like the example
     *              <code>
     *                  "value = ?" or
     *                  "value = ? AND other = ?" also
     *                  "value = ? OR other = ? AND another = ?"
     *              <code/>
     * @param args set the values for the where clause in order of use
     * @return the number of row that got deleted or zero if non
     * */
    public int delete(String where, String ...args){
        return db.getWritableDatabase().delete(table, where, args);
    }
}
