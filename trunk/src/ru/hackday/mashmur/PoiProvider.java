package ru.hackday.mashmur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PoiProvider {
    public List<Poi> points = new LinkedList<Poi>();
    public static final int E6 = 1000000;

    public PoiProvider() {
        points.add(new Poi(60 * E6, 30 * E6, "description1", "name1", "http://ya.ru/audio1"));
        points.add(new Poi(65 * E6, 35 * E6, "description2", "name3", "http://ya.ru/audio2"));
        points.add(new Poi(70 * E6, 40 * E6, "description3", "name3", "http://ya.ru/audio3"));
        points.add(new Poi(75 * E6, 50 * E6, "description4", "name4", "http://ya.ru/audio4"));
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