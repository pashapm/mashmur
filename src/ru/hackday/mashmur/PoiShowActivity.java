package ru.hackday.mashmur;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PoiShowActivity extends Activity {
	
	Poi mPoi;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_item);
        mPoi = getIntent().getExtras().getParcelable("poi");
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(mPoi.getName());
        TextView desc = (TextView) findViewById(R.id.desc);
        desc.setText(mPoi.getDescription());
    }
}
