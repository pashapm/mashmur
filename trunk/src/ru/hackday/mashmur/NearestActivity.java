package ru.hackday.mashmur;

import android.os.Bundle;
import com.google.android.maps.MapActivity;

public class NearestActivity extends MapActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);
        
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
