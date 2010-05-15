package ru.hackday.mashmur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class HomeGridAdapter extends BaseAdapter {

    public enum ItemType {QR, NEAR, FAV, SEARCH}

    private List<Item> items;

    private Context context;

    public HomeGridAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
    }

    public HomeGridAdapter(Context context) {
        items = new ArrayList<Item>();

        Intent intent;
        
        intent = new Intent(context, QrCallerActivity.class);
        items.add(new Item(ItemType.QR, context.getString(R.string.get_qr), R.drawable.get_qr,intent));

        intent = new Intent(context, NearestActivity.class);
        items.add(new Item(ItemType.NEAR, context.getString(R.string.nearest), R.drawable.nearest,intent));

        intent = new Intent(context, FavActivity.class);
        items.add(new Item(ItemType.FAV, context.getString(R.string.fav), R.drawable.fav, intent));

        intent = new Intent(context, SearchActivity.class);
        items.add(new Item(ItemType.FAV, context.getString(R.string.search), R.drawable.search, intent));

        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < items.size())
            return items.get(position);
        else
            return null;
    }
    
    @Override
    public long getItemId(int position) {
        if (position < items.size())
            return items.get(position).getType().ordinal();
        else
            return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position >= items.size())
            return null;

        LinearLayout ly = (LinearLayout) ((Activity) context).getLayoutInflater().inflate(R.layout.homegrid_cell, null);

        TextView tv = (TextView) ly.getChildAt(1);
        tv.setText(getItem(position).toString());

        ImageView iv = (ImageView) ly.getChildAt(0);
        iv.setImageResource(items.get(position).getImageId());

        return ly;
    }

    public class Item {

        private ItemType type;
        private String title;
        private int imageId;
        private Intent intent;

        public Item(ItemType type, String title, int imageId) {
            this(type, title, imageId, null);
        }

        public Item(ItemType type, String title, int imageId, Intent intent) {
            this.type = type;
            this.title = title;
            this.imageId = imageId;
            this.intent = intent;
        }

        public ItemType getType() {
            return type;
        }

        public void setType(ItemType type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public Intent getIntent() {
            return intent;
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        @Override
        public String toString() {
            return title;
        }
    }

}
