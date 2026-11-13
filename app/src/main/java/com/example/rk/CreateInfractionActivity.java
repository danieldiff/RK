package com.example.rk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CreateInfractionActivity extends AppCompatActivity {
    public static List<String> infractionList = new ArrayList<>(); // Temporary storage

    private EditText etInfractionType, etDate, etDetails;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_infraction);

        studentId = getIntent().getStringExtra(DashboardActivity.EXTRA_STUDENT_ID);

        etInfractionType = findViewById(R.id.etInfractionType);
        etDate = findViewById(R.id.etDate);
        etDetails = findViewById(R.id.etDetails);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);

        btnSave.setOnClickListener(v -> saveRecord());
        btnBack.setOnClickListener(v -> finish());
    }

    private void saveRecord() {
        String type = etInfractionType.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String details = etDetails.getText().toString().trim();

        // Validation
        if (type.isEmpty()) {
            etInfractionType.setError("Please enter infraction type");
            return;
        }
        if (date.isEmpty()) {
            etDate.setError("Please enter a date");
            return;
        }

        // Save to temporary list
        String record = "📅 " + date + "  |  ⚠️ " + type;
        if (!details.isEmpty()) {
            record += "\n📝 " + details;
        }
        record += "\n👤 Student ID: " + studentId;

        infractionList.add(0, record); // Add to top of list (newest first)

        Toast.makeText(this, "✅ Record Saved!", Toast.LENGTH_SHORT).show();

        // Clear form
        etInfractionType.setText("");
        etDate.setText("");
        etDetails.setText("");

        // Go back to dashboard
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
