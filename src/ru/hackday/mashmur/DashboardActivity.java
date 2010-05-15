package ru.hackday.mashmur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;


public class DashboardActivity extends Activity implements OnItemClickListener{
    /**
     * Called when the activity is first created.
     */
	private HomeGridAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homegrid);
        GridView mHomeGrid = (GridView) findViewById(R.id.HomeGrid);
        adapter = new HomeGridAdapter(this);
        mHomeGrid.setAdapter(adapter);
        mHomeGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setProgressBarIndeterminateVisibility(true);

        HomeGridAdapter.Item item = (HomeGridAdapter.Item) DashboardActivity.this.adapter.getItem(position);
        Intent intent = item.getIntent();

        startActivity(intent);
    }
}
