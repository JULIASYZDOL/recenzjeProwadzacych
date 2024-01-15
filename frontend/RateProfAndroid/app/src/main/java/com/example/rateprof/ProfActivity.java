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

public class ProfActivity extends AppCompatActivity{
    private DataViewModel dataViewModel;
    private AdapterProfList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);

        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView ocenaJakTextView = findViewById(R.id.ocenaJakTextView);
        TextView ocenaTruTextView = findViewById(R.id.ocenaTruTextView);
        TextView komentarzeTextView = findViewById(R.id.komentarzeTextView);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String searchText = "";
        if (intent != null) {
            searchText = intent.getStringExtra("SELECTED_NAME");

            if (searchText != null && !searchText.isEmpty()) {
                resultTextView.setText("Oceny prowadzącego " + searchText + ": ");
            } else {
                resultTextView.setText("Oceny prowadzącego");
            }
        }

        dataViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DataViewModel.class);

        String nazwaProwadzacego = searchText;
        ApiInterface apiInterface = ApiClient.getClient();
        assert nazwaProwadzacego != null;
        dataViewModel.fetchOcenaJak(nazwaProwadzacego, apiInterface);

        dataViewModel.getNamesLiveDataOcenyJak().observe((LifecycleOwner) this, new Observer<Double>() {
            @Override
            public void onChanged(Double ocena) {
                ocenaJakTextView.setText("Średnia ocena jakości prowadzenia zajęć: " + String.valueOf(ocena));
            }
        });

        dataViewModel.fetchOcenaTru(nazwaProwadzacego, apiInterface);

        dataViewModel.getNamesLiveDataOcenyTru().observe((LifecycleOwner) this, new Observer<Double>() {
            @Override
            public void onChanged(Double ocena) {
                ocenaTruTextView.setText("Średnia ocena trudności zaliczenia zajęć: " + String.valueOf(ocena));
            }
        });

        komentarzeTextView.setText("Komentarze: ");

        dataViewModel.fetchTresci(nazwaProwadzacego, apiInterface);

        dataViewModel.getNamesLiveDataTresci().observe((LifecycleOwner) this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> tresci) {
                adapter = new AdapterProfList(tresci, ProfActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });


    }
}
