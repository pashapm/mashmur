package ru.hackday.mashmur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


public class DashboardActivity extends Activity implements OnItemClickListener {

    private HomeGridAdapter adapter;
    private final int QR_REQUEST = 42;


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

        startActivityForResult(intent, QR_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == QR_REQUEST) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                Log.d("########", contents);

                Intent i = new Intent(this, QrCallerActivity.class);
                i.putExtra(QrCallerActivity.QR_CONTENT, contents);
                startActivity(i);

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }
}
