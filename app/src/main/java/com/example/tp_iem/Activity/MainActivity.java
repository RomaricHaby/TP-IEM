package com.example.tp_iem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tp_iem.Data.ApiClient;
import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.Modele.Character.DataCharacterApi;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.CharacterAdapter;
import com.example.tp_iem.UI.Adapter.CustomRecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private ApiInterface apiService;

    private ArrayList<Character>characterArrayList;

    private CustomRecyclerView customRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth.getInstance().signOut();


        configureBottomView();

        apiService = ApiClient.getClient().create(ApiInterface.class);


        recyclerView = findViewById(R.id.recyclerViewCharacter);


        customRecyclerView = new CustomRecyclerView(recyclerView, apiService,this);

    }





    private void configureBottomView(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
    }

    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.action_android:

                break;
            case R.id.action_logo:

                break;
            case R.id.action_landscape:

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