package com.example.admin.play;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumSongsActivity extends AppCompatActivity {
    private final ArrayList<Songs> songsList = new ArrayList();
    private MediaPlayer mediaFile;
    private ImageButton btnPause, btnPlay, btnStop, btnNext, btnPrevious;
    private AudioManager mAudioManager;
    private int positionPrev = 0;
    private int positionNext = 0;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }

    };
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        public void onAudioFocusChange(int focusChange){
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaFile.pause();
                mediaFile.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaFile.start();

            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_songs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        populateListView();
        onStop();


    }
    public void populateListView() {

        songsList.add(new Songs("Jay Z", "Kill jayZ", R.drawable.jigga, R.raw.kill_jay_z));
        songsList.add(new Songs("Jay Z", "Marcy me", R.drawable.jigga, R.raw.marcy_me));
        songsList.add(new Songs("Jay Z", "Legacy", R.drawable.jigga, R.raw.legacy));
        songsList.add(new Songs("Jay Z", "Caught their eyes feat frank ocean", R.drawable.jigga, R.raw.caught_their_eyes_feat_frank_ocean));
        songsList.add(new Songs("Jay Z", "Family fued", R.drawable.jigga, R.raw.family_feud));
        songsList.add(new Songs("Jay Z", "Marcy me", R.drawable.jigga, R.raw.marcy_me));
        songsList.add(new Songs("Jay Z", "Moonlight", R.drawable.jigga, R.raw.moonlight));
        songsList.add(new Songs("Jay Z", "Smile feat gloria carter", R.drawable.jigga, R.raw.smile_feat_gloria_carter));

        SongsAdapter adapter = new SongsAdapter(this, songsList, R.color.category_music);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();

                Songs theSongs = songsList.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaFile = MediaPlayer.create(AlbumSongsActivity.this, theSongs.getmAudioResourceId());

                    mediaFile.start();

                    mediaFile.setOnCompletionListener(mCompletionListener);

                }
            }

        });

        btnPause = (ImageButton) findViewById(R.id.btnPause);

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaFile.pause();
            }
        });
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaFile.start();
            }
        });
        btnStop = (ImageButton) findViewById(R.id.btnStop);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaFile.stop();
            }
        });
        btnPrevious = (ImageButton) findViewById(R.id.previous_ibtn);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaFile != null && mediaFile.isPlaying()){
                    mediaFile.stop();
                }

                Songs tracks = songsList.get(positionPrev--);
                mediaFile = mediaFile.create(AlbumSongsActivity.this, tracks.getAudioResourceId());


                mediaFile.start();
            }
        });

        btnNext = (ImageButton) findViewById(R.id.next_ibtn);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaFile != null && mediaFile.isPlaying()){
                    mediaFile.stop();
                }

                Songs tracks = songsList.get(positionNext++);
                mediaFile =mediaFile.create(AlbumSongsActivity.this, tracks.getAudioResourceId());


                mediaFile.start();
            }
        });



    }

    protected void onStop() {

        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaFile != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaFile.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaFile = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
