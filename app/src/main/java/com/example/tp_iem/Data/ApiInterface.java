package com.example.tp_iem.Data;

import com.example.tp_iem.Modele.Character.DataCharacterApi;
import com.example.tp_iem.Modele.Location.DataLocationApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("Content-Type: application/json")

    @GET("location/")
    Call<DataLocationApi> getLocation();


    @GET("character/")
    Call<DataCharacterApi> getCharacter();
}
