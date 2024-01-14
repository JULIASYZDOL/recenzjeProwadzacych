package com.example.rateprof;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(view -> {
            String searchText = searchEditText.getText().toString();

            Intent intent = new Intent(NextActivity.this, ProfListActivity.class);

            intent.putExtra("SEARCH_TEXT", searchText);

            startActivity(intent);
        });
    }
}
