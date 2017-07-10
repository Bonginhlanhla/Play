package com.example.admin.play;

import java.security.PublicKey;

/**
 * Created by Admin on 7/6/2017.
 */

public class Songs  {

    private String artistName, songTitle;
    private int mImageResourceId;
    private int mAudioResourceId;

    public Songs(String artistName, String songTitle, int audioResourceId) {
        this.artistName = artistName;
        this.songTitle = songTitle;
        mAudioResourceId = audioResourceId;
    }

    public Songs(String artistName, String songTitle, int mImageResourceId, int mAudioResourceId) {
        this.artistName = artistName;
        this.songTitle = songTitle;
        this.mImageResourceId = mImageResourceId;
        this.mAudioResourceId = mAudioResourceId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setEnglishTranslation(String artistName) {
        this.artistName = artistName;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    public void setmAudioResourceId(int mAudioResourceId) {
        this.mAudioResourceId = mAudioResourceId;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

}
