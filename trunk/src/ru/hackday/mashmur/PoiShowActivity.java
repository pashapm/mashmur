package ru.hackday.mashmur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ru.hackday.mashmur.media.MediaPlayerDemo;
import ru.hackday.mashmur.media.MediaPlayerDemo_Audio;


public class PoiShowActivity extends Activity implements OnClickListener{
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
        setContentView(R.layout.poi_show_view);
        mPoi = getIntent().getExtras().getParcelable("poi");
        TextView name = (TextView) findViewById(R.id.show_name);
        name.setText(mPoi.getName());
        TextView desc = (TextView) findViewById(R.id.show_desc);
        desc.setText(mPoi.getDescription());
        Button playButton = (Button) findViewById(R.id.show_play);
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
        
        final ImageView imgview = (ImageView) findViewById(R.id.show_image);
        
        new AsyncTask<String, Void, Bitmap>() {

			@Override
			protected void onPostExecute(Bitmap result) {
				if (result!=null) {
					imgview.setImageBitmap(result);	
				}
				
			}

			@Override
			protected Bitmap doInBackground(String... params) {
				Bitmap bm = null;
				try {
					byte[] img = getFileFromUrl(params[0]);
					bm = BitmapFactory.decodeByteArray(img, 0, img.length);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return bm;
			} 
        	
        }.execute(mPoi.getImageUrl());
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

	@Override
	public void onClick(View v) {
		final String url = mPoi.getAudioUrl();
//		final String url = "http://www.wyntonmarsalis.org/audio/tribute/wyntonstribute.mp3";
		 
		new Thread() {

			@Override
			public void run() {
				try {
					byte[] audiofile = getFileFromUrl(url);
					FileOutputStream out = new FileOutputStream(new File("/sdcard/test.mp3"));
					out.write(audiofile);
					out.close();
					Log.d("PoiShowActivity", "Downloaded!11");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
		}.start();

		
	}
	
	public byte[] getFileFromUrl(String url) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();
        if (url == null) return new byte[]{};
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        byte[] result = null;
        if (httpEntity != null) {
            try {
                result = HttpClientHelper.httpEntityToByteArray(httpEntity);
            }
            catch (InterruptedIOException e) {
                httpGet.abort();
                throw e;
            }
            finally {
                httpEntity.consumeContent();
            }
        }
        return result;
    }
 
}
