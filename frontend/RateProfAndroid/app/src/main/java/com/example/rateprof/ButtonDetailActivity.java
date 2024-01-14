package com.example.rateprof;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ButtonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_detail);

        TextView textView = findViewById(R.id.textView);

        String buttonText = getIntent().getStringExtra("BUTTON_TEXT");

        textView.setText(buttonText);
    }
}

