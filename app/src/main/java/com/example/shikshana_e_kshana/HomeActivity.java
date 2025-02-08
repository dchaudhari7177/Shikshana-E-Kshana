package com.example.shikshana_e_kshana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
        TextView  b = findViewById(R.id.kannada_button);
        TextView E = findViewById(R.id.english_button);
        TextView  S = findViewById(R.id.science_button);
        TextView  M = findViewById(R.id.math_button);
        TextView  H = findViewById(R.id.history_button);
        TextView  G = findViewById(R.id.geography_button);
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
        if (b != null) {
            b.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, KannadaOption.class);
                startActivity(intent);
            });
        } else {
            System.out.println("user_profile ImageView not found!");
        }
        if (E != null) {
            E.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this,EnglishOption.class);
                startActivity(intent);
            });
        } else {
            System.out.println("user_profile ImageView not found!");
        }
        if (S != null) {
            S.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, ScienceOption.class);
                startActivity(intent);
            });
        } else {
            System.out.println("user_profile ImageView not found!");
        }  if (M != null) {
            M.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, MathematicsOption.class);
                startActivity(intent);
            });
        } else {
            System.out.println("user_profile ImageView not found!");
        }  if (G != null) {
            G.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, SocialOption.class);
                startActivity(intent);
            });
        } else {
            System.out.println("user_profile ImageView not found!");
        }
        if (H != null) {
            H.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, HindiOption.class);
                startActivity(intent);
            });
        } else {
            System.out.println("user_profile ImageView not found!");
        }




    }
}
