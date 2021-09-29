package com.example.tp_iem.UI.Adapter.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class CharacterAdapterViewHolder extends RecyclerView.ViewHolder{

    private ImageView imageView;
    private TextView name;
    private TextView gender;
    private TextView life;

    private Context context;


    public CharacterAdapterViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;

        imageView = itemView.findViewById(R.id.img_vh_character);
        name = itemView.findViewById(R.id.name_vh_character);
        gender = itemView.findViewById(R.id.gender_vh_character);
        life = itemView.findViewById(R.id.life_vh_character);
    }

    public void updateWithGithubUser(Character character){
        //Load picture
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(character.getImage())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(imageView);

        name.setText(character.getName());
        gender.setText(character.getGender());
        life.setText(character.getStatus());
    }
}
