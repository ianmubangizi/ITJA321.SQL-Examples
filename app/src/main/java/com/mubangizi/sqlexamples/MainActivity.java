package com.mubangizi.sqlexamples;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mubangizi.sqlexamples.data.model.User;
import com.mubangizi.sqlexamples.data.table.Users;

public class MainActivity extends AppCompatActivity {

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try (SqlDbOpenHelper db = new SqlDbOpenHelper(this, "example.db")) {
            Users users_table = new Users(db);

            Button save = findViewById(R.id.save_button);
            TextView name_text = findViewById(R.id.person_name);
            TextView email_text = findViewById(R.id.person_email);

            save.setOnClickListener((view) -> {
                double magic_number = 125.175 / 5 * 8 / 5;
                int id = (int) (Math.random() * magic_number);

                String name = name_text.getText().toString();
                String email = email_text.getText().toString();

                User user = new User(id, name, email, "");
                long result = users_table.save(user);
                if (result > 0) {
                    Toast.makeText(getApplicationContext(),
                            "Add You Successfully",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Something happened, please try again",
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}