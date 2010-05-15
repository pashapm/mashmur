package ru.hackday.mashmur;

import com.google.android.maps.GeoPoint;

public class Poi {
    public static final int E6 = 1000000;
    
    int latitudeE6;
    int longitudeE6;
    String description;
    String name;
    String audioUrl;

    public Poi(int latitudeE6, int longitudeE6, String description, String name, String audioUrl) {
        this.latitudeE6 = latitudeE6;
        this.longitudeE6 = longitudeE6;
        this.description = description;
        this.name = name;
        this.audioUrl = audioUrl;
    }

    public GeoPoint makeGeoPoint() {
        return new GeoPoint(latitudeE6, longitudeE6);
    }

}
