package com.example.tp_iem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tp_iem.Data.ApiClient;
import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.Modele.Character.DataCharacterApi;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.CharacterAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth.getInstance().signOut();


        configureBottomView();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

       /* apiService.getLocation().enqueue(new Callback<ResultLocation>() {
            @Override
            public void onResponse(Call<ResultLocation> call, Response<ResultLocation> response) {

                Log.d("getLocation","Response = "+ response.toString());


                Log.d("getLocation","Response = "+ response.body().getLocations().get(1).getDimension());

            }

            @Override
            public void onFailure(Call<ResultLocation> call, Throwable t) {
                Log.d("getLocation","Throwable = "+t.toString());

            }
        });*/

        apiService.getCharacter().enqueue(new Callback<DataCharacterApi>() {
            @Override
            public void onResponse(Call<DataCharacterApi> call, Response<DataCharacterApi> response) {
                setRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<DataCharacterApi> call, Throwable t) {

            }
        });
    }

    public void setRecyclerView(DataCharacterApi dataCharacterApi){
        recyclerView = findViewById(R.id.recyclerViewCharacter);

        // Create adapter passing in the sample user data
        CharacterAdapter adapter = new CharacterAdapter(dataCharacterApi.getCharacters());
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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