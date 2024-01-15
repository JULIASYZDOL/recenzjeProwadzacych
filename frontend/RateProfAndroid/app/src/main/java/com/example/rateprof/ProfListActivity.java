package com.example.rateprof;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfListActivity extends AppCompatActivity {
    private DataViewModel dataViewModel;
    private AdapterProf adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proflist);

        TextView resultTextView = findViewById(R.id.resultTextView);

        Intent intent = getIntent();
        String searchText = "";
        if (intent != null) {
            searchText = intent.getStringExtra("SELECTED_NAME");

            if (searchText != null && !searchText.isEmpty()) {
                resultTextView.setText("Lista prowadzących na uczelni " + searchText + ": ");
            } else {
                resultTextView.setText("Lista prowadzących: ");
            }
        }


        dataViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DataViewModel.class);

        String nazwaUczelni = searchText;
        ApiInterface apiInterface = ApiClient.getClient();
        assert nazwaUczelni != null;
        dataViewModel.fetchData(nazwaUczelni, apiInterface);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dataViewModel.getNamesLiveData().observe((LifecycleOwner) this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> names) {
                adapter = new AdapterProf(names, ProfListActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

}
