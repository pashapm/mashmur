package ru.hackday.mashmur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class QrCallerActivity extends Activity {

	public static String QR_CONTENT = "qr_content";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tw = new TextView(this);
        tw.setText(getIntent().getStringExtra(QR_CONTENT));
        setContentView(tw);
    }

}
