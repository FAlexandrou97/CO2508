package com.example.assignmentstart;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "liked_songs")
public class LikedSong implements Serializable {

    @PrimaryKey()
    private int trackID;
    private int commonTrackID;
    private String trackName;
    private int position;
    private String date_liked;
    private String country_code;
    private String artist_name;
    private String album_name;

    @ForeignKey(entity = country.class,
            parentColumns = "code",
            childColumns = "country_code",
            onDelete = ForeignKey.CASCADE)


    public LikedSong(int trackID, int commonTrackID, String trackName, int position, String date_liked, String country_code, String artist_name, String album_name) {
        this.trackID = trackID;
        this.commonTrackID = commonTrackID;
        this.trackName = trackName;
        this.position = position;
        this.date_liked = date_liked;
        this.country_code = country_code;
        this.artist_name = artist_name;
        this.album_name = album_name;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public int getCommonTrackID() {
        return commonTrackID;
    }

    public void setCommonTrackID(int commonTrackID) {
        this.commonTrackID = commonTrackID;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDate_liked() {
        return date_liked;
    }

    public void setDate_liked(String date_liked) {
        this.date_liked = date_liked;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    @Override
    public String toString() {
        return this.trackName;
    }
}
