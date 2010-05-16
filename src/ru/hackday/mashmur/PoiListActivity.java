package ru.hackday.mashmur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import ru.hackday.mashmur.media.MediaPlayerDemo;
import ru.hackday.mashmur.media.MediaPlayerDemo_Audio;

import java.io.File;
import java.util.List;

public class PoiListActivity extends Activity implements ILocationChangedListener {
    private PoiProvider poiProvider;
    private List<Poi> poiList;
    private PoiListAdapter adapter;
    private SensorManager mSensorManager;

    //TODO make listActivity?
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_list);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        MyLocationHelper.setLocationListener(this, this);
        poiProvider = new PoiProvider();
//        updateWithNewLocation(null);
        poiList = poiProvider.getNearest(67 * Poi.E6, 60 * Poi.E6, 100);
        ListView listView = (ListView) findViewById(R.id.poi_list);
        adapter = new PoiListAdapter();
        listView.setAdapter(adapter);

    }

    public void updateWithNewLocation(Location location) {
        if (poiProvider == null) return;
        poiList = poiProvider.getNearest(67 * Poi.E6, 60 * Poi.E6, 100);
    }


    private class PoiListAdapter extends BaseAdapter {


        public int getCount() {
            return poiList.size();
        }

        public Object getItem(int i) {
            return i;
        }

        public long getItemId(int i) {
            return i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Poi poi = poiList.get(i);
            LayoutInflater factory = LayoutInflater.from(PoiListActivity.this);
            View itemView = factory.inflate(R.layout.poi_item, null);
            TextView name = (TextView) itemView.findViewById(R.id.name);
            name.setText(poi.getName());
            TextView description = (TextView) itemView.findViewById(R.id.desc);
            description.setText(poi.description);
            Button playButton = (Button) itemView.findViewById(R.id.play);
            //!!! set your path
            String testPath = "/sdcard/test.mp3";
            final Uri audioFileUri = Uri.fromFile(new File(testPath));
            playButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent =
                            new Intent(PoiListActivity.this.getApplication(),
                                    MediaPlayerDemo_Audio.class);
                    intent.putExtra(MediaPlayerDemo.EXTRA_AUDIO_URI, audioFileUri.toString());
                    startActivity(intent);
                }
            });
            CompassView compassView = (CompassView) findViewById(R.id.compass);
            return itemView;
        }
    }

    private final SensorListener mListener = new SensorListener() {

        public void onSensorChanged(int sensor, float[] values) {
            if (Config.LOGD)
                Log.d(this.getClass().getCanonicalName(), "sensorChanged (" + values[0] + ", " + values[1] + ", " + values[2] + ")");
            CompassView.mValues = values;
            adapter.notifyDataSetChanged();

        }

        public void onAccuracyChanged(int sensor, int accuracy) {

        }
    };

    @Override
    protected void onResume() {
        if (Config.LOGD) Log.d(getClass().getCanonicalName(), "onResume");
        super.onResume();
        mSensorManager.registerListener(mListener,
                SensorManager.SENSOR_ORIENTATION,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        if (Config.LOGD) Log.d(getClass().getCanonicalName(), "onStop");
        mSensorManager.unregisterListener(mListener);
        super.onStop();
    }
}
