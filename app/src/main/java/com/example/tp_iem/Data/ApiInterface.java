package com.example.tp_iem.Data;

import com.example.tp_iem.Modele.Character.DataCharacterApi;
import com.example.tp_iem.Modele.Episode.DataEpisodeApi;
import com.example.tp_iem.Modele.Location.DataLocationApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("Content-Type: application/json")

    @GET("location/")
    Call<DataLocationApi> getLocation(@Query("page") int page);

    @GET("character/")
    Call<DataCharacterApi> getCharacter(@Query("page") int page);

    @GET("episode/")
    Call<DataEpisodeApi> getEpisode(@Query("page") int page);

    @GET("character/")
    Call<DataCharacterApi> getCharacterFilter(@Query("name") String name);


}