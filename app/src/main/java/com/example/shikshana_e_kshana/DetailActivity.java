package com.example.shikshana_e_kshana;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {
    private ShapeableImageView profileImage;
    private EditText etName, etClass, etDescription;
    private Bitmap selectedImageBitmap;
    private DatabaseHelper databaseHelper;

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        profileImage.setImageBitmap(selectedImageBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        databaseHelper = new DatabaseHelper(this);
        initializeViews();
        loadSavedProfile();
    }

    private void initializeViews() {
        profileImage = findViewById(R.id.profileImage);
        etName = findViewById(R.id.etName);
        etClass = findViewById(R.id.etClass);
        etDescription = findViewById(R.id.etDescription);
        TextView feedbackTextView = findViewById(R.id.tvFeedback);

        Button selectImageButton = findViewById(R.id.selectImageButton);
        Button btnSave = findViewById(R.id.btnSave);

        selectImageButton.setOnClickListener(v -> openImageChooser());
        btnSave.setOnClickListener(v -> saveProfile());

        feedbackTextView.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    private void saveProfile() {
        String name = etName.getText().toString().trim();
        String className = etClass.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (name.isEmpty() || className.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImageBitmap == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert bitmap to byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Profile profile = new Profile(name, className, description, byteArray);
        long result = databaseHelper.insertProfile(profile);

        if (result != -1) {
            Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save profile", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSavedProfile() {
        Profile profile = databaseHelper.getProfile();
        if (profile != null) {
            etName.setText(profile.getName());
            etClass.setText(profile.getClassName());
            etDescription.setText(profile.getDescription());

            Bitmap bitmap = profile.getImageBitmap();
            if (bitmap != null) {
                profileImage.setImageBitmap(bitmap);
                selectedImageBitmap = bitmap;
            }
        }
    }
}