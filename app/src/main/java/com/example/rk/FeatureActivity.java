package com.example.rk;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/** Informational destination used until the related feature is implemented. */
public class FeatureActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.rk.FEATURE_TITLE";
    public static final String EXTRA_MESSAGE = "com.example.rk.FEATURE_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView content = new TextView(this);
        content.setText(getIntent().getStringExtra(EXTRA_TITLE) + "\\n\\n"
                + getIntent().getStringExtra(EXTRA_MESSAGE) + "\\n\\nTap Back to return to the dashboard.");
        content.setTextSize(18);
        content.setTextColor(0xFF202124);
        content.setPadding(48, 64, 48, 48);
        content.setBackgroundColor(0xFFF5F6FA);
        setContentView(content);
    }
}
