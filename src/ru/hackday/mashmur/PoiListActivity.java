package ru.hackday.mashmur;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
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

    //TODO make listActivity?
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_list);
//        MyLocationHelper.setLocationListener(this, this);
        poiProvider = new PoiProvider();
//        updateWithNewLocation(null);
        poiList = poiProvider.getNearest(67 * Poi.E6, 60 * Poi.E6, 100);
        ListView listView = (ListView) findViewById(R.id.poi_list);
        listView.setAdapter(new PoiListAdapter());

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
            return itemView;
        }
    }
}
