package com.example.rateprof;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ProfListActivity extends AppCompatActivity {
    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proflist);

        TextView resultTextView = findViewById(R.id.resultTextView);

        Intent intent = getIntent();
        String searchText = "";
        if (intent != null) {
            searchText = intent.getStringExtra("SEARCH_TEXT");

            resultTextView.setText("Lista prowadzących na uczelni " + searchText + ": ");
        }

        setContentView(R.layout.activity_proflist);

        dataViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DataViewModel.class);

        String nazwaUczelni = searchText;
        ApiInterface apiInterface = ApiClient.getClient();
        try {
            dataViewModel.fetchData(nazwaUczelni, apiInterface);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dataViewModel.getNamesLiveData().observe((LifecycleOwner) this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> names) {
                if (names != null) {
                    generateButtonList(names);
                }
            }
        });
    }

    private List<String> generateButtonList(List<String> nazwy) {
        List<String> buttonList = new ArrayList<>();
        for(int i = 0; i < nazwy.size(); i++){
            buttonList.add(nazwy.get(i));
        }
        return buttonList;
    }
}
