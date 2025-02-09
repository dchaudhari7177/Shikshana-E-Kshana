package com.example.shikshana_e_kshana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    private static final String TAG = "FeedbackActivity"; // Debug Tag
    private FirebaseFirestore db;
    private EditText feedbackEditText;
    private RadioGroup radioGroup1, radioGroup2;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        db = FirebaseFirestore.getInstance(); // Initialize Firestore

        feedbackEditText = findViewById(R.id.feedbackEditText);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> saveFeedback());
    }

    private void saveFeedback() {
        String feedback = feedbackEditText.getText().toString().trim();
        String performance = getSelectedRadioText(radioGroup1);
        String uiRating = getSelectedRadioText(radioGroup2);

        if (performance == null || uiRating == null) {
            Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("performance", performance);
        feedbackData.put("uiRating", uiRating);
        feedbackData.put("feedback", feedback);
        feedbackData.put("timestamp", System.currentTimeMillis());

        db.collection("feedback")
                .add(feedbackData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Feedback submitted!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Feedback submitted: " + documentReference.getId());

                    // Redirect to DetailsActivity
                    Intent intent = new Intent(FeedbackActivity.this, DetailActivity.class);
                    startActivity(intent);
                    finish(); // Close the FeedbackActivity so the user can't go back
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error submitting feedback", e);
                });
    }

    private String getSelectedRadioText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) return null; // Return null if nothing selected
        RadioButton selectedRadio = findViewById(selectedId);
        return selectedRadio.getText().toString();
    }
}
