package com.example.rk;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_ID = "com.example.rk.STUDENT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardactivity);

        // Initialize UI Components
        TextView greetingText = findViewById(R.id.greetingText);
        LinearLayout btnCreateNew = findViewById(R.id.btnCreateNew);
        LinearLayout btnViewRecords = findViewById(R.id.btnViewRecords);
        Button profileButton = findViewById(R.id.profileButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Display Student Name or Default
        String studentId = getIntent().getStringExtra(EXTRA_STUDENT_ID);
        if (studentId != null && !studentId.trim().isEmpty()) {
            greetingText.setText("Hello, " + studentId);
        } else {
            greetingText.setText("Hello, studentName");
        }

        // Navigate to Create Infraction
        if (btnCreateNew != null) {
            btnCreateNew.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, CreateInfractionActivity.class);
                startActivity(intent);
            });
        }

        // Navigate to View Records
        if (btnViewRecords != null) {
            btnViewRecords.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, ViewRecordsActivity.class);
                startActivity(intent);
            });
        }

        // My Profile Action
        if (profileButton != null) {
            profileButton.setOnClickListener(v -> {
                // Add profile navigation here if needed
            });
        }

        // Logout Action
        if (logoutButton != null) {
            logoutButton.setOnClickListener(v -> finish());
        }
    }
}
