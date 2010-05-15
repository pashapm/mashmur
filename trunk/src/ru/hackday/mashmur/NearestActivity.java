package ru.hackday.mashmur;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ZoomControls;
import com.google.android.maps.*;

import java.util.ArrayList;
import java.util.List;

public class NearestActivity extends MapActivity {
    LinearLayout linearLayout;
    MapView mapView;
    ZoomControls zoomControls;

    List<Overlay> mapOverlays;
    Drawable drawable;
    NearestOverlay itemizedOverlay;

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
        itemizedOverlay = new NearestOverlay(drawable);

        GeoPoint point = new GeoPoint(19240000, -99120000);
        OverlayItem overlayitem = new OverlayItem(point, "point1", "point2");
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
