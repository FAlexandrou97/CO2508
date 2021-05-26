package com.example.assignmentstart;

import androidx.appcompat.app.AppCompatActivity;

public class ItemTrack extends AppCompatActivity {

    private String trackName;
    private String artistName;
    private boolean liked;

    public ItemTrack(String trackName, String artistName, boolean liked) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.liked = liked;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() { return artistName; }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
