package com.example.rk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final rk2 recordkeeper = rk2.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        EditText idInput = findViewById(R.id.emailInput);
        EditText pinInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(view -> {
            String id = idInput.getText().toString().trim();
            String pin = pinInput.getText().toString();

            Account account = recordkeeper.login(id, pin);
            if (account == null) {
                Toast.makeText(this, "Invalid student ID or PIN.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.putExtra(DashboardActivity.EXTRA_STUDENT_ID, account.getID());
            startActivity(intent);
        });

        signUpButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SignupActivity.class)));
    }
}
