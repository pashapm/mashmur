package ru.hackday.mashmur;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class Poi {
    public static final int E6 = 1000000;

    int latitudeE6;
    int longitudeE6;
    String description;
    String name;
    String audioUrl;

    public Poi() {
    }

    public int getLatitudeE6() {
        return latitudeE6;
    }

    public void setLatitudeE6(int latitudeE6) {
        this.latitudeE6 = latitudeE6;
    }

    public int getLongitudeE6() {
        return longitudeE6;
    }

    public void setLongitudeE6(int longitudeE6) {
        this.longitudeE6 = longitudeE6;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

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

    public OverlayItem makeOverlayItem() {
        return new OverlayItem(makeGeoPoint(), name, description);
    }

    @Override
    public String toString() {
        return "Poi{" +
                "latitudeE6=" + latitudeE6 +
                ", longitudeE6=" + longitudeE6 +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                '}';
    }
}
