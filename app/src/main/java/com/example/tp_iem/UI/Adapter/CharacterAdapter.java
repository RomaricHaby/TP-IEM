package com.example.tp_iem.UI.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_iem.Activity.MainActivity;
import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.Modele.User;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.ViewHolder.CharacterAdapterViewHolder;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapterViewHolder>{

    private final List<Character> characterList;
    private MainActivity activity;

    public CharacterAdapter(List<Character> characterList, MainActivity activity) {
        this.characterList = characterList;
        this.activity = activity;
    }


    @Override
    public CharacterAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_holder_character, parent, false);

        return new CharacterAdapterViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(CharacterAdapterViewHolder viewHolder, int position) {
        viewHolder.updateCharacter(this.characterList.get(position));

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Character character = characterList.get(position);

                if (User.getInstance().isFav(character)){
                    User.getInstance().removeFavCharacter(character);
                }
                else{
                    User.getInstance().addFavCharac(character);
                    activity.saveCharacter();
                }

                viewHolder.updateFav(character);
                return false;
            }
        });

    }


    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void  addData(List<Character> list){
        characterList.addAll(list);
    }

    public List<Character> getCharacterList() {
        return characterList;
    }
}



