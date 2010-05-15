package ru.hackday.mashmur;

import android.location.Location;

public interface ILocationChangedListener {
    void updateWithNewLocation(Location location);
}
