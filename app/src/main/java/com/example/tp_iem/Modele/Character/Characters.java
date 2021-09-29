package com.example.tp_iem.Modele.Character;

import com.example.tp_iem.Modele.Info;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Characters {


    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<ResultCharacter> resultCharacters = null;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<ResultCharacter> getResultCharacters() {
        return resultCharacters;
    }

    public void setResultCharacters(List<ResultCharacter> resultCharacters) {
        this.resultCharacters = resultCharacters;
    }

}


