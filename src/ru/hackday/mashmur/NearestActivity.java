package ru.hackday.mashmur;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.android.maps.*;

import java.util.List;

import static ru.hackday.mashmur.Poi.E6;

public class NearestActivity extends MapActivity {
    LinearLayout linearLayout;
    MapView mapView;

    List<Overlay> mapOverlays;
    Drawable drawable;
    NearestOverlay itemizedOverlay;
    TrackOverlay trackOverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);
        linearLayout = (LinearLayout) findViewById(R.id.zoomview);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.androidmarker);

        itemizedOverlay = new NearestOverlay(drawable, this);
        trackOverlay = new TrackOverlay();

        PoiProvider poiProvider = new PoiProvider();
        List<Poi> points = poiProvider.getNearest(60 * E6, 30 * E6, 10);
        for (Poi point : points) {
            GeoPoint point1 = new GeoPoint(point.latitudeE6, point.longitudeE6);
            OverlayItem overlayitem1 = new OverlayItem(point1, point.name, point.description);
            itemizedOverlay.addOverlay(overlayitem1);
            trackOverlay.addGeoPoint(point1);
        }

        mapOverlays.add(trackOverlay);
        mapOverlays.add(itemizedOverlay);

    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
