package com.example.tp_iem.UI.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_iem.Activity.MainActivity;
import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.Modele.Character.DataCharacterApi;
import com.example.tp_iem.Modele.Location.DataLocationApi;
import com.example.tp_iem.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomRecyclerView {

    private final String TAG = "CustomRecyclerView";
    private final RecyclerView recyclerView;
    private final ApiInterface apiService;
    private final Context context;
    private  CharacterAdapter characterAdapter;
    private  LocalisationAdapter localisationAdapter;
    private MainActivity activity;

    private int page;
    private int type;

    private String nameCharacter;

    public CustomRecyclerView(RecyclerView recyclerView, ApiInterface apiService, Context context, MainActivity activity) {
        this.recyclerView = recyclerView;
        this.apiService = apiService;
        this.context = context;
        this.page = 1;
        this.activity = activity;

        setRecyclerViewScroll();

    }


    //Scroll view
    public void setRecyclerViewScroll(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    switch (type){
                        case 1:
                            getLocationCallback();
                            break;
                        case 2:
                            if (!nameCharacter.equals("")){
                                getCharacterFilter();
                            }
                            else{
                                getCharacterCallback();
                            }
                            break;
                        case 3:

                            break;

                    }
                }
            }
        });
    }

    //Request for charact with page
    public void getCharacterCallback(){
        this.apiService.getCharacter(page).enqueue(new Callback<DataCharacterApi>() {

            @Override
            public void onResponse(@NonNull Call<DataCharacterApi> call, @NonNull Response<DataCharacterApi> response) {
                //Init recycler view
                if (getPage() == 1){
                    if (response.body() != null) {
                        setRecyclerViewCharacter(response.body());
                    }
                }
                else{
                    if (response.body() != null && getPage() != response.body().getInfo().getPages() + 1) {
                        getCharacterAdapter().addData(response.body().getCharacters());
                        getCharacterAdapter().notifyDataSetChanged();

                    }
                }
                addPage();
            }

            @Override
            public void onFailure(@NonNull Call<DataCharacterApi> call, @NonNull Throwable t) {
                Log.e(TAG, "Throwable" + t);
            }
        });
    }


    //Request for charact with page
    public void getCharacterFilter(){
        this.apiService.getCharacterFilter(nameCharacter).enqueue(new Callback<DataCharacterApi>() {

            @Override
            public void onResponse(@NonNull Call<DataCharacterApi> call, @NonNull Response<DataCharacterApi> response) {
                //Init recycler view
                if (getPage() == 1){
                    if (response.body() != null) {
                       setRecyclerViewCharacter(response.body());
                    }                
                }
                else{
                    if (response.body() != null && response.body().getInfo().getNext() != null) {
                        getCharacterAdapter().addData(response.body().getCharacters());
                        getCharacterAdapter().notifyDataSetChanged();
                    }
                }
                addPage();
            }

            @Override
            public void onFailure(@NonNull Call<DataCharacterApi> call, @NonNull Throwable t) {
                Log.e(TAG, "Throwable" + t);
            }
        });
    }

    //Request for location with page
    public void getLocationCallback(){
        this.apiService.getLocation(page).enqueue(new Callback<DataLocationApi>() {
            @Override
            public void onResponse(Call<DataLocationApi> call, Response<DataLocationApi> response) {
                //Init recycler view
                if (getPage() == 1){
                    if (response.body() != null) {
                        setRecyclerViewLocalisation(response.body());
                    }
                }
                else{
                    if (response.body() != null && getPage() != response.body().getInfoLocation().getPages() + 1) {
                        getLocalisationAdapter().addData(response.body().getLocations());
                        getLocalisationAdapter().notifyDataSetChanged();

                    }
                }
                addPage();
            }

            @Override
            public void onFailure(Call<DataLocationApi> call, Throwable t) {
                Log.e(TAG, "Throwable" + t);
            }
        });
    }

    //Init recycler view
    private void setRecyclerViewCharacter(DataCharacterApi dataCharacterApi){
        // Create adapter passing in the sample user data
        setCharacterAdapter(new CharacterAdapter(dataCharacterApi.getCharacters(), activity));
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(getCharacterAdapter());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    //Init recycler view
    private void setRecyclerViewLocalisation(DataLocationApi dataLocationApi){
        // Create adapter passing in the sample user data
        setLocalisationAdapter(new LocalisationAdapter(dataLocationApi.getLocations()));
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(getLocalisationAdapter());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }



    // Get && set
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void addPage() {
        this.page++;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public CharacterAdapter getCharacterAdapter() {
        return this.characterAdapter;
    }

    public void setCharacterAdapter(CharacterAdapter characterAdapter) {
        this.characterAdapter = characterAdapter;
    }

    public LocalisationAdapter getLocalisationAdapter() {
        return localisationAdapter;
    }

    public void setLocalisationAdapter(LocalisationAdapter localisationAdapter) {
        this.localisationAdapter = localisationAdapter;
    }

    public String getNameCharacter() {
        return nameCharacter;
    }

    public void setNameCharacter(String nameCharacter) {
        this.nameCharacter = nameCharacter;
    }
}
