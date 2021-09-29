package com.example.tp_iem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tp_iem.Data.ApiClient;
import com.example.tp_iem.Data.ApiInterface;
import com.example.tp_iem.Modele.Character.Characters;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.CharacterAdapter;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth.getInstance().signOut();




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

        apiService.getCharacter().enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                setRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {

            }
        });
    }

    public void setRecyclerView(Characters characters){
        recyclerView = findViewById(R.id.recyclerViewCharacter);

        // Create adapter passing in the sample user data
        CharacterAdapter adapter = new CharacterAdapter(characters.getResultCharacters());
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}