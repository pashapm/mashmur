package ru.hackday.mashmur;

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

    public PoiProvider() {
        points.add(new Poi(60 * Poi.E6, 30 * Poi.E6, "description1description1description1description1description1description1description1description1description1description1description1description1description1description1description1description1", "name1", "http://ya.ru/audio1"));
        points.add(new Poi(61 * Poi.E6, 35 * Poi.E6, "description2", "name3", "http://ya.ru/audio2"));
        points.add(new Poi(62 * Poi.E6, 25 * Poi.E6, "description3description3description3description3description3description3description3", "name3", "http://ya.ru/audio3"));
        points.add(new Poi(64 * Poi.E6, 50 * Poi.E6, "description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4description4", "name4", "http://ya.ru/audio4"));
    }

    public void init() throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://citymurmur.ru/kml/");
        HttpResponse response = httpclient.execute(httpGet);
        InputStream is = response.getEntity().getContent();
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