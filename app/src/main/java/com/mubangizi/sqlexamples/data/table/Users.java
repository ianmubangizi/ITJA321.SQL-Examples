package com.mubangizi.sqlexamples.data.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.mubangizi.sqlexamples.data.Table;
import com.mubangizi.sqlexamples.data.model.User;

import java.util.ArrayList;

public class Users extends Table {

    public Users(SQLiteOpenHelper database) {
        super(database, "users", "id", "name", "email", "password");
    }

    public ArrayList<User> getAll(@Nullable String where, String... args) {
        ArrayList<User> users = new ArrayList<>();
        try (Cursor result = get(where, args)) {
            do {
                if (result.moveToNext()) {
                    int id = result.getInt(result.getColumnIndex("id"));
                    String name = result.getString(result.getColumnIndex("name"));
                    String email = result.getString(result.getColumnIndex("email"));
                    String password = result.getString(result.getColumnIndex("password"));
                    users.add(new User(id, name, email, password));
                }
            } while (!result.isAfterLast());
        } catch (Exception ex) {
            Log.d("USERS SELECTION FAILED",
                    "in Users.getAll: [ex] -> ["
                            + ex.getMessage() + "]");
        }
        return users;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public User getById(int id) {
        ArrayList<User> result = getAll("id = ?", Integer.toString(id));
        return result.stream().findFirst().filter((user -> user.getId() == id)).get();
    }

    public long save(User user) {
        ContentValues content = new ContentValues();
        content.put("id", user.getId());
        content.put("name", user.getName());
        content.put("email", user.getEmail());
        content.put("password", user.getPassword());
        return insert(content);
    }
}
