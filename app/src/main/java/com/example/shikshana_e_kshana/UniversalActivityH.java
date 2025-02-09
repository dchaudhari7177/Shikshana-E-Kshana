
package com.example.shikshana_e_kshana;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shikshana_e_kshana.R;

public class UniversalActivityH extends AppCompatActivity {

    private String selectedClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_h);

        // Get the selected class
        selectedClass = getIntent().getStringExtra("selectedClass");

        if (selectedClass != null) {
            Toast.makeText(this, "Selected: " + selectedClass, Toast.LENGTH_SHORT).show();
        }

        // Set different links for different classes
        setMaterialLinks();
    }

    private void setMaterialLinks() {
        Button button1 = findViewById(R.id.button1); // TextBook PDF
        Button button2 = findViewById(R.id.button2); // Notes PDF
        Button button3 = findViewById(R.id.button3); // Sample Papers

        button1.setOnClickListener(v -> openPDF(getTextbookLink()));
        button2.setOnClickListener(v -> openPDF(getNotesLink()));
        button3.setOnClickListener(v -> openPDF(getSamplePaperLink()));
    }

    private void openPDF(String url) {
        if (url != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Material not available", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTextbookLink() {
        switch (selectedClass) {
            case "Class 5": return "https://drive.google.com/file/d/18ljaCzLurBipmeuFBAA9C2sEgXRvgsG-/view?usp=drive_link";
            case "Class 6": return "https://drive.google.com/file/d/18ljaCzLurBipmeuFBAA9C2sEgXRvgsG-/view?usp=drive_link";
            case "Class 7": return "https://drive.google.com/file/d/18ljaCzLurBipmeuFBAA9C2sEgXRvgsG-/view?usp=drive_link";
            case "Class 8": return "https://drive.google.com/file/d/18ljaCzLurBipmeuFBAA9C2sEgXRvgsG-/view?usp=drive_link";
            case "Class 9": return "https://drive.google.com/file/d/18ljaCzLurBipmeuFBAA9C2sEgXRvgsG-/view?usp=drive_link";
            case "Class 10": return "https://drive.google.com/file/d/18ljaCzLurBipmeuFBAA9C2sEgXRvgsG-/view?usp=drive_link";
            default: return null;
        }
    }

    private String getNotesLink() {
        switch (selectedClass) {
            case "Class 5": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 6": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 7": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 8": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 9": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 10": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            default: return null;
        }
    }

    private String getSamplePaperLink() {
        switch (selectedClass) {
            case "Class 5": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 6": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 7": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 8": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 9": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            case "Class 10": return "https://drive.google.com/file/d/1FO_8BcxceDhPFR1vHVdc4FkWdeTwxga5/view?usp=drive_link";
            default: return null;
        }
    }
}
