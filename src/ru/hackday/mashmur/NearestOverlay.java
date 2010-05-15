package ru.hackday.mashmur;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

public class NearestOverlay extends ItemizedOverlay {

    private ArrayList<Poi> mOverlays = new ArrayList<Poi>();
    private Context context;

    public NearestOverlay(Drawable drawable, Context context) {
        super(boundCenterBottom(drawable));
        this.context = context;
    }

    @Override
    public int size() {
        return mOverlays.size();
    }

    public void addPoi(Poi poi) {
        mOverlays.add(poi);
        populate();
    }

    @Override
    protected OverlayItem createItem(int i) {
        return mOverlays.get(i).makeOverlayItem();
    }

    @Override
    protected boolean onTap(int index) {
        Poi poi = mOverlays.get(index);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(poi.getName());
        dialog.setMessage(poi.getDescription());
        dialog.setPositiveButton("listen!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "todo:", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton(android.R.string.cancel, null);
        dialog.show();
        return true;
    }


}