package ru.hackday.mashmur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ru.hackday.mashmur.media.MediaPlayerDemo;
import ru.hackday.mashmur.media.MediaPlayerDemo_Audio;

import java.awt.image.SampleModel;

public class PoiShowActivity extends Activity {
    CompassView compassView;
    private SensorManager mSensorManager;
    Poi mPoi;
    private final SensorListener mListener = new SensorListener() {

        public void onSensorChanged(int sensor, float[] values) {
            CompassView.mValues = values;
            if (compassView != null) {
                compassView.invalidate();
            }
        }

        public void onAccuracyChanged(int sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        setContentView(R.layout.poi_item);
        mPoi = getIntent().getExtras().getParcelable("poi");
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(mPoi.getName());
        TextView desc = (TextView) findViewById(R.id.desc);
        desc.setText(mPoi.getDescription());
        Button playButton = (Button) findViewById(R.id.play);
        playButton.setVisibility(View.VISIBLE);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent =
                        new Intent(PoiShowActivity.this.getApplication(),
                                MediaPlayerDemo_Audio.class);
                //todo set uri for audio
                intent.putExtra(MediaPlayerDemo.EXTRA_AUDIO_URI, mPoi.audioUrl);
                startActivity(intent);
            }
        }
        );
        compassView = (CompassView) findViewById(R.id.compass);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mListener,
                SensorManager.SENSOR_ORIENTATION,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(mListener);
        super.onStop();
    }

}
