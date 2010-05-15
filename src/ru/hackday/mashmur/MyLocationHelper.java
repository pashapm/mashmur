package ru.hackday.mashmur;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MyLocationHelper {

    public static void setLocationListener(final ILocationChangedListener listener, Context context) {
        String locationContext = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) context.getSystemService(locationContext);
        //set criteria
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        //location provider
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        listener.updateWithNewLocation(location);
        final LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                listener.updateWithNewLocation(location);
            }

            public void onProviderDisabled(String provider) {
                listener.updateWithNewLocation(null);
            }

            public void onProviderEnabled(String provider) {
            }

            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }
        };
        locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
    }

}
