package com.example.tp_iem.UI.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_iem.Modele.Episode.Episode;
import com.example.tp_iem.R;

public class EpisodeViewHolder extends RecyclerView.ViewHolder {
    private TextView episode_tv;
    private TextView air_date;
    private  TextView name;

    public EpisodeViewHolder(View view) {
        super(view);

        episode_tv = view.findViewById(R.id.episode_tv);
        air_date = view.findViewById(R.id.air_date_tv);
        name = view.findViewById(R.id.name_episode_tv);
    }

    public void updateEpisode(Episode episode) {

        episode_tv.setText(episode.getEpisode());
        air_date.setText(episode.getAirDate());
        name.setText(episode.getName());
    }
}
