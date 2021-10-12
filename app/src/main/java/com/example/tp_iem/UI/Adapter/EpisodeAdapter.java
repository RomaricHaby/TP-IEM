package com.example.tp_iem.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_iem.Modele.Episode.Episode;
import com.example.tp_iem.Modele.Location.Location;
import com.example.tp_iem.R;
import com.example.tp_iem.UI.Adapter.ViewHolder.EpisodeViewHolder;
import com.example.tp_iem.UI.Adapter.ViewHolder.LocalisationViewHolder;

import java.util.List;


public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeViewHolder> {
    private final List<Episode> episodeList;

    public EpisodeAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }


    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_holder_episode, parent, false);

        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder episodeViewHolder, int position) {
        episodeViewHolder.updateEpisode(this.episodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public void  addData(List<Episode> list){
        episodeList.addAll(list);
    }
}
