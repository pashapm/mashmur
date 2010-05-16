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
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import ru.hackday.mashmur.R;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MediaPlayerDemo_Audio extends Activity {

    private MediaPlayer mMediaPlayer;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private Timer timer;
    private Uri uri;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.audio_view);
        uri = Uri.parse(getIntent().getStringExtra(MediaPlayerDemo.EXTRA_AUDIO_URI));
        progressBar = (ProgressBar) findViewById(R.id.progressId);
        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                playAudio();
                if (mMediaPlayer != null) {
                    timer = new Timer();
                    timer();
                }
            }
        });
        final Button pauseBtn = (Button) findViewById(R.id.pauseBtn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.pause();
                }
            }
        });
        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mMediaPlayer != null) {
                    setProgress(0, 1);
                    timer.cancel();
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                    mMediaPlayer = null;

                }
            }
        });

    }

    private void timer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (mMediaPlayer != null) {
                    setProgress(mMediaPlayer.getCurrentPosition(), mMediaPlayer.getDuration());
                }
            }
        }, 500, 30);
    }

    private void playAudio() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        } else {
            try {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(MediaPlayerDemo_Audio.this, uri);
                mMediaPlayer.prepare();
                mMediaPlayer.start();


            } catch (IOException e) {
                if (mMediaPlayer
                        != null) {
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }


    public void setProgress(final long currentSize, final long totalSize) {

        handler.post(new Runnable() {
            public void run() {
                progressBar.setProgress((int) (100 * currentSize / totalSize));
            }
        });

    }
}
