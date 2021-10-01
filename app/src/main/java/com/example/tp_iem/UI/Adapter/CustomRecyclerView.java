package com.example.tp_iem.UI.Adapter;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.Modele.Character.DataCharacterApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomRecyclerView {

    private final String TAG = "CustomRecyclerView";
    private final RecyclerView recyclerView;
    private final ApiInterface apiService;
    private final Context context;
    private  CharacterAdapter adapter;

    private int page;


    public CustomRecyclerView(RecyclerView recyclerView, ApiInterface apiService, Context context) {
        this.recyclerView = recyclerView;
        this.apiService = apiService;
        this.context = context;
        this.page = 1;

        setRecyclerViewScroll();
        getCharacterCallback();
    }

    public int getPage() {
        return page;
    }

    public void addPage() {
        this.page++;
    }

    public CharacterAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(CharacterAdapter adapter) {
        this.adapter = adapter;
    }

    //Scroll view
    public void setRecyclerViewScroll(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    getCharacterCallback();
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
                        setRecyclerView(response.body());
                    }
                }
                else{
                    if (response.body() != null ) {
                        if (response.body().getInfo().getNext() != null ){
                            getAdapter().addData(response.body().getCharacters());
                            getAdapter().notifyDataSetChanged();
                        }
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

    //Init recycler view
    private void setRecyclerView(DataCharacterApi dataCharacterApi){
        // Create adapter passing in the sample user data
        setAdapter(new CharacterAdapter(dataCharacterApi.getCharacters()));
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(getAdapter());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

}
