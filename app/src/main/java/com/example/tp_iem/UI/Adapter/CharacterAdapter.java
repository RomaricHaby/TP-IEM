package com.example.tp_iem.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_iem.Modele.Character.ResultCharacter;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.ViewHolder.CharacterAdapterViewHolder;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapterViewHolder>{


    //FOR DATA
    private List<ResultCharacter> resultCharacterList;

    public CharacterAdapter(List<ResultCharacter> resultCharacterList) {
        this.resultCharacterList = resultCharacterList;
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
        viewHolder.updateWithGithubUser(this.resultCharacterList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultCharacterList.size();
    }

}



