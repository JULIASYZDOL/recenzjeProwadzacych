package com.example.rateprof;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {
    private final MutableLiveData<List<String>> namesLiveData = new MutableLiveData<>();

    public LiveData<List<String>> getNamesLiveData() {
        return namesLiveData;
    }

    public void fetchData(String nazwaUczelni, ApiInterface apiInterface) throws IOException {
        Call<Integer> callIdUczelni = apiInterface.getIdUczelni(nazwaUczelni);
        callIdUczelni.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    int idUczelni = response.body();

                    Call<List<String>> callNames = apiInterface.getNamesByUczelni(idUczelni);
                    callNames.enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                            if (response.isSuccessful()) {
                                namesLiveData.setValue(response.body());
                            } else {
                                handleFailure();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<String>> call, Throwable t) {
                            handleFailure();
                        }
                    });
                } else {
                    handleFailure();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                handleFailure();
            }
        });
    }

    private void handleFailure() {
        namesLiveData.setValue(null);
    }
}

