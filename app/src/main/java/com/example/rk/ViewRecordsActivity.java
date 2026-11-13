package com.example.rk;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        String studentId = getIntent().getStringExtra(DashboardActivity.EXTRA_STUDENT_ID);

        TextView tvStudentInfo = findViewById(R.id.tvStudentInfo);
        TextView tvEmpty = findViewById(R.id.tvEmpty);
        ListView recordsListView = findViewById(R.id.recordsListView);
        Button btnBack = findViewById(R.id.btnBack);

        tvStudentInfo.setText("Student ID: " + studentId);

        // Load records
        if (CreateInfractionActivity.infractionList.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recordsListView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recordsListView.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    CreateInfractionActivity.infractionList
            );
            recordsListView.setAdapter(adapter);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
