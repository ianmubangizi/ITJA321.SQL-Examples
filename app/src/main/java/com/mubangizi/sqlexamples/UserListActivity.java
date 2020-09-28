package com.mubangizi.sqlexamples;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mubangizi.sqlexamples.data.model.User;
import com.mubangizi.sqlexamples.data.table.Users;
import java.util.stream.Collectors;

public class UserListActivity extends AppCompatActivity {

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        String[] names = {};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        try (SqlDbOpenHelper db = new SqlDbOpenHelper(this, "example.db")) {
            Users users_table = new Users(db);
            names = users_table.getAll(null)
                    .stream().map((User::getName))
                    .collect(Collectors.toList())
                    .toArray(names);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.user_list_item, R.id.user_list_name, names);

        ListView listView = findViewById(R.id.user_list);
        listView.setAdapter(adapter);
    }
}