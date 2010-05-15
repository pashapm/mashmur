package ru.hackday.mashmur;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ZoomControls;
import com.google.android.maps.*;

import java.util.List;

public class NearestActivity extends MapActivity {
    LinearLayout linearLayout;
    MapView mapView;
    ZoomControls zoomControls;

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
        zoomControls = (ZoomControls) mapView.getZoomControls();
        linearLayout.addView(zoomControls);

        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        itemizedOverlay = new NearestOverlay(drawable, this);

        GeoPoint point1 = new GeoPoint(19240000, -99120000);
        OverlayItem overlayitem1 = new OverlayItem(point1, "point1 title", "point1 title snippet");

        GeoPoint point2 = new GeoPoint(35410000, 139460000);
        OverlayItem overlayitem2 = new OverlayItem(point2, "point2 title", "point2 title snippet");

        GeoPoint point3 = new GeoPoint(17410000, 69460000);
        OverlayItem overlayitem3 = new OverlayItem(point3, "point3 title", "point3 title snippet");

        itemizedOverlay.addOverlay(overlayitem1);
        itemizedOverlay.addOverlay(overlayitem2);
        itemizedOverlay.addOverlay(overlayitem3);
        trackOverlay = new TrackOverlay();
        trackOverlay.addGeoPoint(point1);
        trackOverlay.addGeoPoint(point2);
        trackOverlay.addGeoPoint(point3);
        mapOverlays.add(trackOverlay);
        mapOverlays.add(itemizedOverlay);

    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
