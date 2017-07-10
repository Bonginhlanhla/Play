package com.example.admin.play;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlayViewActivity extends AppCompatActivity {

    private ImageButton btnStop, btnNext, btnPrevious,btnPlay;
    private MediaPlayer mediaFile;
    private AlbumSongsActivity songs = new AlbumSongsActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_view);


//        btnStop = (ImageButton) findViewById(R.id.btnStop);
//        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
//
//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaFile.stop();
//
//            }
//        });
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mediaFile.start();
                songs.populateListView();
            }
        });

    }

}
