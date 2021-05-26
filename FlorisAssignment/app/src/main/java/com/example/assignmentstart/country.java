package com.example.assignmentstart;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class country {

    @PrimaryKey()
    @NonNull
    private String code;
    private String name;
    private String wikipedia;
    private double lat;
    private double lng;
    private int favourite;

    public country(String code, String name, String wikipedia, double lat, double lng) {
        this.code = code;
        this.name = name;
        this.wikipedia = wikipedia;
        this.lat = lat;
        this.lng = lng;
        this.favourite = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }
}
