package com.example.rateprof;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("uczelnie/allNames")
    Call<List<String>> getAllNames();
    @GET("uczelnie/byNazwa/{nazwa}")
    Call<Integer> getIdUczelni(@Path("nazwa") String nazwa);

    @GET("prowadzacy/byUczelni/{id}")
    Call<List<String>> getNamesByUczelni(@Path("id") int id);

    @GET("prowadzacy/byNazwa/{prowadzacy_nazwa}")
    Call<Integer> getIdByName(@Path("prowadzacy_nazwa") String prowadzacy_nazwa);

    @GET("Pseudonimy/{idProwadzacego}")
    Call<List<String>> getPseudonimy(@Path("idProwadzacego") int idProwadzacego);

    @GET("Tresci/{idProwadzacego}")
    Call<List<String>> getTresci(@Path("idProwadzacego") int idProwadzacego);

    @GET("ocenyTrudnosci/srednia/{idProwadzacego}")
    Call<Double> getMeanTruByIdProw(@Path("idProwadzacego") int idProwadzacego);

    @GET("ocenyJakosci/srednia/{idProwadzacego}")
    Call<Double> getMeanJakByIdProw(@Path("idProwadzacego") int idProwadzacego);
}
