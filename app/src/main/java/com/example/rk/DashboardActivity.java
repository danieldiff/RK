package com.example.rk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_ID = "com.example.rk.STUDENT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        String studentId = getIntent().getStringExtra(EXTRA_STUDENT_ID);
        Account account = rk2.getInstance().getAccount(studentId);

        if (account == null) {
            Toast.makeText(this, "Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView greeting = findViewById(R.id.greetingText);
        greeting.setText("Hello, " + account.getName());

        // These destinations are intentionally informational placeholders.
        // Infraction creation/recording is not enabled in this version.
        LinearLayout createCard = findViewById(R.id.createItemCard);
        LinearLayout viewCard = findViewById(R.id.viewItemsCard);
        Button profileButton = findViewById(R.id.profileButton);

        createCard.setOnClickListener(view -> openPlaceholder(
                "Record an infraction", "Infraction recording will be available in a future version."));
        viewCard.setOnClickListener(view -> openPlaceholder(
                "Student records", "There are no records to display in this prototype."));
        profileButton.setOnClickListener(view -> openPlaceholder(
                "My profile", "Student ID: " + account.getID() + "\nName: " + account.getName()));

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void openPlaceholder(String title, String message) {
        Intent intent = new Intent(this, FeatureActivity.class);
        intent.putExtra(FeatureActivity.EXTRA_TITLE, title);
        intent.putExtra(FeatureActivity.EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
