package com.example.tp_iem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp_iem.Data.ApiClient;
import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.CharacterAdapter;
import com.example.tp_iem.UI.Adapter.CustomRecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

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

    }

    public void search (){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customRecyclerView.setPage(1);
                customRecyclerView.getCharacterFilter(search.getText().toString());
            }
        });
    }

    public void setUpRecuclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        customRecyclerView = new CustomRecyclerView(recyclerView, apiService, this);
    }


    public void characterCallback(){
        customRecyclerView.setPage(1);
        customRecyclerView.setType(2);
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
    }

}