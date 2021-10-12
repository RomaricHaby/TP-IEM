package com.example.tp_iem.UI.Adapter;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp_iem.Activity.MainActivity;
import com.example.tp_iem.Modele.User;
import com.example.tp_iem.R;

public class DialogFavCharacter extends Dialog  {
    private RecyclerView recyclerView;
    private  CharacterAdapter characterAdapter;
    private MainActivity mainActivity;

    public DialogFavCharacter(MainActivity a) {
        super(a);
        this.mainActivity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_fav_character);

        recyclerView = findViewById(R.id.recyclerViewFav);

        characterAdapter = new CharacterAdapter(User.getInstance().getArrayFavCharac(), mainActivity);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(characterAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity.getApplicationContext()));
    }

}
