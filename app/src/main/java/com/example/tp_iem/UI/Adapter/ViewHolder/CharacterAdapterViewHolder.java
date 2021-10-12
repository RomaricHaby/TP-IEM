package com.example.tp_iem.UI.Adapter.ViewHolder;


import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp_iem.Modele.Character.Character;
import com.example.tp_iem.Modele.User;
import com.example.tp_iem.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class CharacterAdapterViewHolder extends RecyclerView.ViewHolder{

    private final ImageView imageView;
    private final TextView name;
    private final TextView status;
    private final TextView species;
    private final TextView lastPosition;
    private ImageView fav;

    private final FloatingActionButton buttonLife;

    private final Context context;


    public CharacterAdapterViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;

        imageView = itemView.findViewById(R.id.img_vh_character);
        name = itemView.findViewById(R.id.name_vh_character);

        buttonLife = itemView.findViewById(R.id.isAliveButton);
        status = itemView.findViewById(R.id.status_vh_character);
        species = itemView.findViewById(R.id.species_vh_character);

        lastPosition = itemView.findViewById(R.id.last_position_vh_character);

        fav = itemView.findViewById(R.id.iv_favorite);

    }

    public void updateCharacter(Character character){
        //Load picture
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(character.getImage())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(imageView);


        name.setText(character.getName());
        status.setText(character.getStatus());
        species.setText(character.getSpecies());
        lastPosition.setText(character.getLocation().getName());

        updateFav(character);

        switch (character.getStatus()){
            case "Alive":
                buttonLife.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.life)));
                break;

            case  "Dead":
                buttonLife.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.dead)));
                break;
            default:
                buttonLife.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.unknow)));
                break;
        }
    }

    public void updateFav(Character character){
        if(User.getInstance().isFav(character)){
            fav.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        else{
            fav.setImageResource(R.drawable.ic_baseline_not_favorite_24);
        }
    }
}
