package ru.hackday.mashmur;

import java.util.Comparator;


public class PoiComparator implements Comparator<Poi> {
    private int latitudeE6;
    private int longitudeE6;

    private PoiComparator() {
    }

    public PoiComparator(int latitudeE6, int longitudeE6) {
        this.latitudeE6 = latitudeE6;
        this.longitudeE6 = longitudeE6;
    }

    @Override
    public int compare(Poi o1, Poi o2) {
        long distance1 = getdistance(o1);
        long distance2 = getdistance(o1);
        return (int) (distance1 - distance2);
    }

    private int getdistance(Poi poi) {
        int latDiff = poi.latitudeE6 - latitudeE6;
        int lonDiff = poi.longitudeE6 - longitudeE6;
        return (int) Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }
}
