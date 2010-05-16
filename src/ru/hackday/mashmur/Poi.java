package ru.hackday.mashmur;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class Poi implements Parcelable {
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
    
    //parcelable 
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(latitudeE6);
        out.writeInt(longitudeE6);
        out.writeString(description);
        out.writeString(name);
        out.writeString(audioUrl);
    }

    public static final Parcelable.Creator<Poi> CREATOR
            = new Parcelable.Creator<Poi>() {
        public Poi createFromParcel(Parcel in) {
            return new Poi(in);
        }

        public Poi[] newArray(int size) {
            return new Poi[size];
        }
    };
    
    private Poi(Parcel in) {
        latitudeE6 = in.readInt();
        longitudeE6 = in.readInt();
        description = in.readString();
        name = in.readString();
        audioUrl = in.readString();
    }
}
