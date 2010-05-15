package ru.hackday.mashmur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PoiProvider {
    public List<Poi> points = new LinkedList<Poi>();

    public List<Poi> getNearest(int lat, int lon, int limit) {
        
        Collections.sort(points, new PoiComparator(lat, lon));
        return points;
    }

}
