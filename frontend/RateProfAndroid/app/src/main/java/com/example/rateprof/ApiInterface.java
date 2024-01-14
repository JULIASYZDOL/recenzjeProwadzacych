package com.example.rateprof;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("uczelnie/byNazwa/{nazwa}")
    Call<Integer> getIdUczelni(@Path("nazwa") String nazwa);

    @GET("prowadzacy/byUczelni/{id}")
    Call<List<String>> getNamesByUczelni(@Path("id") int id);
}
