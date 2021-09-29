package com.example.tp_iem.Data;

import com.example.tp_iem.Modele.Character.Characters;
import com.example.tp_iem.Modele.Character.ResultCharacter;
import com.example.tp_iem.Modele.Location.ResultLocation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("Content-Type: application/json")

    @GET("location/")
    Call<ResultLocation> getLocation();


    @GET("character/")
    Call<Characters> getCharacter();
}
