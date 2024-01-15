package com.example.rateprof;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NextActivity extends AppCompatActivity {
    private AdapterProfList adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Lista uczelni: ");

        recyclerView = findViewById(R.id.recyclerView);

        DataViewModel dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        ApiInterface apiInterface = ApiClient.getClient();

        dataViewModel.fetchAllNames(apiInterface);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataViewModel.getNamesLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> names) {
                adapter = new AdapterProfList(names, NextActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });


    }
}