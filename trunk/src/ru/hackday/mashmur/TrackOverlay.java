package ru.hackday.mashmur;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

import java.util.LinkedList;
import java.util.List;


public class TrackOverlay extends Overlay implements Parcelable {
    private List<GeoPoint> points = new LinkedList<GeoPoint>();

    public void addGeoPoint(GeoPoint gp) {
        points.add(gp);
    }

    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
                        long when) {
        Projection projection = mapView.getProjection();
        if (shadow == false) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            Point point = new Point();
            for (int i = 1, pointsSize = points.size(); i < pointsSize; i++) {
                GeoPoint gp1 = points.get(i - 1);
                GeoPoint gp2 = points.get(i);
                paint.setColor(Color.BLUE);
                projection.toPixels(gp1, point);


                Point point2 = new Point();
                projection.toPixels(gp2, point2);
                paint.setStrokeWidth(5);
                paint.setAlpha(120);
                canvas.drawLine(point.x, point.y,
                        point2.x, point2.y, paint);
            }
        }
        return super.draw(canvas, mapView, shadow, when);
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

    }

    public TrackOverlay(List<GeoPoint> points) {
        this.points = points;
    }

    public TrackOverlay() {
    }

    private TrackOverlay(Parcel in) {

    }

    public static final Parcelable.Creator<TrackOverlay> CREATOR =
            new Parcelable.Creator<TrackOverlay>() {
                public TrackOverlay createFromParcel(Parcel in) {
                    return new TrackOverlay(in);
                }

                public TrackOverlay[] newArray(int size) {
                    return new TrackOverlay[size];
                }
            };
}