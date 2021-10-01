package com.example.tp_iem.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.Modele.Location.Location;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.ViewHolder.CharacterAdapterViewHolder;
import com.example.tp_iem.UI.Adapter.ViewHolder.LocalisationViewHolder;

import java.util.List;

public class LocalisationAdapter extends RecyclerView.Adapter<LocalisationViewHolder>{

    private final List<Location> locationList;

    public LocalisationAdapter(List<Location> locations) {
        this.locationList = locations;
    }


    @NonNull
    @Override
    public LocalisationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_holder_localisation, parent, false);

        return new LocalisationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocalisationViewHolder viewHolder, int position) {
        viewHolder.updateLocation(this.locationList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public void  addData(List<Location> list){
        locationList.addAll(list);
    }
}
