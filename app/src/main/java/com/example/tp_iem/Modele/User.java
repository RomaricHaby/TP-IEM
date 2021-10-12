package com.example.tp_iem.Modele;

import com.example.tp_iem.Modele.Character.Character;

import java.util.ArrayList;

public class User {
    private static User instance = null;
    private ArrayList<Character> arrayFavCharac;

    private User() {
        arrayFavCharac = new ArrayList<>();
    }

    public void addFavCharac(Character character){
        boolean save = false;

        for (Character ch : arrayFavCharac) {
            if (ch.getId() == character.getId()) {
                save = true;
                break;
            }
        }

        if (!save){
            arrayFavCharac.add(character);
        }
    }

    public void removeFavCharacter(Character character){
        for (Character ch : arrayFavCharac) {
            if (ch.getId() == character.getId()) {
                arrayFavCharac.remove(ch);
                return;
            }
        }
    }

    public boolean isFav(Character character){
        for (Character ch : arrayFavCharac) {
            if (ch.getId() == character.getId()) {
                return true;
            }
        }
        return false;
    }

    //Get && Set
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public ArrayList<Character> getArrayFavCharac() {
        return arrayFavCharac;
    }


}
