package ru.hackday.mashmur;

import android.content.Context;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PoiProvider {
    public List<Poi> points = new LinkedList<Poi>();

    public PoiProvider(Context context) {
        try {
            init(context, "http://citymurmur.ru/kml/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
  
    public void init(Context context, String url) throws IOException {
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(url);
//        HttpResponse response = httpclient.execute(httpGet);
//        InputStream is = response.getEntity().getContent();
        InputStream is = context.getAssets().open("pois.xml");
        KMLParser parser = new KMLParser(is);
        parser.parse();
        points = parser.mPois;
    }

    public List<Poi> getNearest(int lat, int lon, int limit) {
        LinkedList<Poi> result = new LinkedList<Poi>(points);
        Collections.sort(result, new PoiComparator(lat, lon));
        if (result.size() > limit) {
            result.subList(limit - 1, result.size() - 1).clear();
        }
        return result;
    }
}