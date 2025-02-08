package com.example.shikshana_e_kshana;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth

        // Initialize UI elements
        ImageView userProfile = findViewById(R.id.user_profile);
        ImageView logout = findViewById(R.id.logout); // Get the logout ImageView
        TextView ask = findViewById(R.id.ask_id);
        TextView kannadaButton = findViewById(R.id.kannada_button);
        TextView englishButton = findViewById(R.id.english_button);
        TextView scienceButton = findViewById(R.id.science_button);
        TextView mathButton = findViewById(R.id.math_button);
        TextView historyButton = findViewById(R.id.history_button);
        TextView geographyButton = findViewById(R.id.geography_button);

        // Set click listeners using helper method
        setupClickListener(ask, OptionActivity.class);
        setupClickListener(userProfile, DetailActivity.class);
        setupClickListener(kannadaButton, KannadaOption.class);
        setupClickListener(englishButton, EnglishOption.class);
        setupClickListener(scienceButton, ScienceOption.class);
        setupClickListener(mathButton, MathematicsOption.class);
        setupClickListener(historyButton, HindiOption.class);
        setupClickListener(geographyButton, SocialOption.class);

        // Logout functionality
        logout.setOnClickListener(v -> {
            // Firebase sign out
            mAuth.signOut();

            // Clear shared preferences login state
            SharedPreferences.Editor editor = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE).edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply(); // Ensure changes are saved

            // Redirect to LoginActivity
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear back stack
            startActivity(intent);

            Toast.makeText(HomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
        });
    }


        // Corrected setupClickListener method for both ImageView and TextView
    private void setupClickListener(Object view, Class<?> activityClass) {
        if (view instanceof ImageView) {
            ((ImageView) view).setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, activityClass);
                startActivity(intent);
            });
        } else if (view instanceof TextView) {
            ((TextView) view).setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, activityClass);
                startActivity(intent);
            });
        }
    }
}
