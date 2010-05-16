/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.hackday.mashmur.media;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ru.hackday.mashmur.R;

import java.io.File;

public class MediaPlayerDemo extends Activity {
    public static final String EXTRA_AUDIO_URI = "audio_uri";

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.mediaplayer);
        //!!! set your path
        String testPath = "/sdcard/test.mp3";
        final Uri audioFileUri = Uri.fromFile(new File(testPath));
        Button mlocalaudio = (Button) findViewById(R.id.localaudio);
        mlocalaudio.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent =
                        new Intent(MediaPlayerDemo.this.getApplication(),
                                MediaPlayerDemo_Audio.class);
                intent.putExtra(EXTRA_AUDIO_URI, audioFileUri.toString());
                startActivity(intent);
            }
        });

    }
}
