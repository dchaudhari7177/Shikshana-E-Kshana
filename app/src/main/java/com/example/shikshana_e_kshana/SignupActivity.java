package com.example.shikshana_e_kshana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText nameEditText, signupEmailEditText, signupPasswordEditText;
    Button signupButton;
    TextView loginLink;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Authentication and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Link UI elements
        nameEditText = findViewById(R.id.nameEditText);
        signupEmailEditText = findViewById(R.id.signupEmailEditText);
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText);
        signupButton = findViewById(R.id.signupButton);
        loginLink = findViewById(R.id.loginLink);

        // Handle signup button click
        signupButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = signupEmailEditText.getText().toString().trim();
            String password = signupPasswordEditText.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(name, email, password);
            }
        });

        // Handle login link click
        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(SignupActivity.this, "User Registered: " + user.getEmail(), Toast.LENGTH_SHORT).show();

                            // Redirect to LoginActivity immediately
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish(); // Close SignupActivity

                            // Save data in Firestore asynchronously
                            saveUserToFirestore(user.getUid(), name, email);
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup Failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }



    private void saveUserToFirestore(String userId, String name, String email) {
        DocumentReference userRef = db.collection("users").document(userId);
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);

        userRef.set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(SignupActivity.this, "Signup Successful! Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Close SignupActivity
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(SignupActivity.this, "Error saving user: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
    }

}
