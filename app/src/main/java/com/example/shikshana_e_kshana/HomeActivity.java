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

        // Initialize UI elements
        ImageView userProfile = findViewById(R.id.user_profile);
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
    }

    /**
     * Helper method to set an onClickListener for a TextView or ImageView
     */
    private void setupClickListener(TextView textView, Class<?> activityClass) {
        if (textView != null) {
            textView.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, activityClass);
                startActivity(intent);
            });
        }
    }

    private void setupClickListener(ImageView imageView, Class<?> activityClass) {
        if (imageView != null) {
            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, activityClass);
                startActivity(intent);
            });
        }
    }
}
