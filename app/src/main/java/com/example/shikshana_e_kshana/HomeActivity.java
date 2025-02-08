package com.example.shikshana_e_kshana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView userProfile = findViewById(R.id.user_profile);
        TextView ask = findViewById(R.id.ask_id);

        // Ensure 'ask' is not null
        if (ask != null) {
            ask.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, OptionActivity.class);
                startActivity(intent);
            });
        } else {
            System.out.println("ask_id TextView not found!");
        }

        if (userProfile != null) {
            userProfile.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                startActivity(intent);
            });
        } else {
            System.out.println("user_profile ImageView not found!");
        }
    }
}
