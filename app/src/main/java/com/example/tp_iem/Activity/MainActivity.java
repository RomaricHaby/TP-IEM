package com.example.tp_iem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tp_iem.Data.ApiClient;
import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.CustomRecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiService;
    private   CustomRecyclerView customRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth.getInstance().signOut();

        apiService = ApiClient.getClient().create(ApiInterface.class);


        // Bottom Navigation View
        configureBottomView();

        // Recycler View
        setUpRecuclerView();

        characterCallback();
    }


    public void setUpRecuclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
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