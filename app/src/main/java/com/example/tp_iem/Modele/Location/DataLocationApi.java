package com.example.tp_iem.Modele.Location;

import com.example.tp_iem.Modele.Info;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultLocation {

    @SerializedName("info")
    @Expose
    private Info infoLocation;
    @SerializedName("results")
    @Expose
    private List<Location> locations = new ArrayList<Location>();

    public Info getInfoLocation() {
        return infoLocation;
    }

    public void setInfoLocation(Info infoLocation) {
        this.infoLocation = infoLocation;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

}