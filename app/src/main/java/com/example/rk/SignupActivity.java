package com.example.rk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private final rk2 recordkeeper = rk2.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        EditText nameInput = findViewById(R.id.etName);
        EditText idInput = findViewById(R.id.etEmail);
        EditText pinInput = findViewById(R.id.etPassword);
        Button signUpButton = findViewById(R.id.btnSignUp);
        TextView loginLink = findViewById(R.id.tvLogin);

        signUpButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString().trim();
            String id = idInput.getText().toString().trim();
            String pin = pinInput.getText().toString();

            if (name.isEmpty() || id.isEmpty() || pin.isEmpty()) {
                Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pin.matches("\\d{4}")) {
                Toast.makeText(this, "PIN must contain exactly 4 digits.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!recordkeeper.createAccount(name, id, pin)) {
                Toast.makeText(this, "That student ID is already registered.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Account created. You can now log in.", Toast.LENGTH_SHORT).show();
            finish();
        });

        loginLink.setOnClickListener(view -> finish());
    }
}
