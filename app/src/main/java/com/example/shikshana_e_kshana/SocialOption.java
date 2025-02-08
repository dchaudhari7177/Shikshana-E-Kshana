package com.example.shikshana_e_kshana;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SocialOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_option);

        // Buttons for different classes
        setButtonClick(R.id.class5Button, "Class 5");
        setButtonClick(R.id.class6Button, "Class 6");
        setButtonClick(R.id.class7Button, "Class 7");
        setButtonClick(R.id.class8Button, "Class 8");
        setButtonClick(R.id.class9Button, "Class 9");
        setButtonClick(R.id.class10Button, "Class 10");
    }

    private void setButtonClick(int buttonId, final String className) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialOption.this,UniversalActivitySS.class);
                intent.putExtra("selectedClass", className); // Pass class info
                startActivity(intent);
            }
        });
    }
}
