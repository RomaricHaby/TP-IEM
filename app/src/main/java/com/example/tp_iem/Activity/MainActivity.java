package com.example.tp_iem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp_iem.Data.ApiClient;
import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.Modele.User;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.CharacterAdapter;
import com.example.tp_iem.UI.Adapter.CustomRecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String TAG = "CharactersFav";
    private ApiInterface apiService;
    private   CustomRecyclerView customRecyclerView;
    private EditText search;
    private Button searchButton;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth.getInstance().signOut();

        apiService = ApiClient.getClient().create(ApiInterface.class);
        search = findViewById(R.id.edit_search);
        searchButton = findViewById(R.id.searchButton);

        // Bottom Navigation View
        configureBottomView();

        // Recycler View
        setUpRecuclerView();

        characterCallback();
        search();

        loadCharacter();
    }

    public void search (){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customRecyclerView.setPage(1);
                customRecyclerView.setNameCharacter(search.getText().toString());
                customRecyclerView.getCharacterFilter();
            }
        });
    }

    public void setUpRecuclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        customRecyclerView = new CustomRecyclerView(recyclerView, apiService, this, this);
    }


    public void characterCallback(){
        customRecyclerView.setPage(1);
        customRecyclerView.setType(2);
        customRecyclerView.setNameCharacter("");
        customRecyclerView.getCharacterCallback();
    }

    public void locationCallback(){
        customRecyclerView.setPage(1);
        customRecyclerView.setType(1);
        customRecyclerView.getLocationCallback();
    }
    private void configureBottomView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.action_character);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateActivityNav(item.getItemId()));
    }

    private Boolean updateActivityNav(Integer integer){
        switch (integer) {
            case R.id.action_location:
                locationCallback();

                break;
            case R.id.action_character:
                characterCallback();

                break;
            case R.id.action_episode:

                break;
        }
        return true;
    }


    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
        saveCharacter();
    }


    public void saveCharacter(){
        Gson gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences(TAG, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Map<String, ?> allEntries = sharedPreferences.getAll();
        Log.e("test", allEntries.toString());
        Log.e("test", User.getInstance().getArrayFavCharac().toString());

        editor.clear();

        for (Character character : User.getInstance().getArrayFavCharac()) {
            if (!allEntries.isEmpty()){
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    if (!entry.getKey().contains(String.valueOf(character.getId()))){
                        String json = gson.toJson(character);
                        editor.putString(String.valueOf(character.getId()), json);
                    }
                }
            }
            else {
                String json = gson.toJson(character);
                editor.putString(String.valueOf(character.getId()), json);
            }
        }
        editor.apply();
    }


    public void loadCharacter(){
        Gson gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences(TAG, 0);

        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String json = sharedPreferences.getString(entry.getKey(),"");
            Log.e("test","json :" +  json);
            Character character = gson.fromJson(json, Character.class);
            User.getInstance().getArrayFavCharac().add(character);
        }
    }
}