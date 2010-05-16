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
    NearestOverlay itemsOverlay;
//    TrackOverlay trackOverlay;

    private List<Poi> points;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);
        linearLayout = (LinearLayout) findViewById(R.id.zoomview);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView.getController().setZoom(14);
        mapView.getController().animateTo(new GeoPoint((int) (60.05*E6), (int) (30.05*E6)));

        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.androidmarker);

        itemsOverlay = new NearestOverlay(drawable, this);
//        trackOverlay = new TrackOverlay();

        PoiProvider poiProvider = new PoiProvider();
        List<Poi> points = poiProvider.getNearest(65 * E6, 35 * E6, 10);
        for (Poi point : points) {
            itemsOverlay.addPoi(point);
//            trackOverlay.addGeoPoint(point.makeGeoPoint());
        }

//        mapOverlays.add(trackOverlay);
        mapOverlays.add(itemsOverlay);

    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
