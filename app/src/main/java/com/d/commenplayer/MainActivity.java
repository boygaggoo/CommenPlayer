package com.d.commenplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.d.commenplayer.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private IjkVideoView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlayer();
    }

    private void initPlayer() {
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        player = (IjkVideoView) findViewById(R.id.player);
//        player.setHudView(hudView);

        player.setVideoPath("http://vpls.cdn.videojj.com/scene/video02_720p.mp4");
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.toggleAspectRatio();
            }
        });
        player.start();
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.release(true);
        }
        super.onDestroy();
    }
}
