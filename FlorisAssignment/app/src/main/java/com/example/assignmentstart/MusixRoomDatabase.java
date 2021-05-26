package com.example.assignmentstart;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {country.class, LikedSong.class}, version = 1)
public abstract class MusixRoomDatabase extends RoomDatabase {

    public abstract countriesDao countriesDao();

    public abstract liked_songsDao liked_songsDao();

    private static volatile MusixRoomDatabase INSTANCE;

    public static MusixRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MusixRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MusixRoomDatabase.class, "musix_database")
                            .allowMainThreadQueries()
                            .build();

                    //Pre-populate database
                    if (INSTANCE.countriesDao().getAllCountries().size() == 0) {
                        INSTANCE.countriesDao().insert(new country("cy", "Cyprus", "https://en.wikipedia.org/wiki/Cyprus", 35.1846799, 33.3815137));
                        INSTANCE.countriesDao().insert(new country("uk", "United Kingdom", "https://en.wikipedia.org/wiki/United_Kingdom", 51.5059627, -0.1303084));
                        INSTANCE.countriesDao().insert(new country("es", "Spain", "https://en.wikipedia.org/wiki/Spain", 40.4177564, -3.7014356));
                        INSTANCE.countriesDao().insert(new country("us", "United States", "https://en.wikipedia.org/wiki/United_States", 38.9031488, -77.0386076));
                        INSTANCE.countriesDao().insert(new country("it", "Italy", "https://en.wikipedia.org/wiki/Italy", 41.9036247, 12.4940977));
                        INSTANCE.countriesDao().insert(new country("gr", "Greece", "https://en.wikipedia.org/wiki/Greece", 37.9845095, 23.708333));
                        INSTANCE.countriesDao().insert(new country("de", "Germany", "https://en.wikipedia.org/wiki/Germany", 52.5199702, 13.4095845));
                        INSTANCE.countriesDao().insert(new country("au", "Australia", "https://en.wikipedia.org/wiki/Australia", -33.8693064, 151.2081572));
                        INSTANCE.countriesDao().insert(new country("ca", "Canada", "https://en.wikipedia.org/wiki/Canada", 45.4185831, -75.6989785));
                        INSTANCE.countriesDao().insert(new country("fr", "France", "https://en.wikipedia.org/wiki/France", 48.8544281, 2.3506222));
                        INSTANCE.countriesDao().insert(new country("jp", "Japan", "https://en.wikipedia.org/wiki/Japan", 35.6764531, 139.7655345));
                        INSTANCE.countriesDao().insert(new country("se", "Sweden", "https://en.wikipedia.org/wiki/Sweden", 59.3244233, 18.0648436));
                    }
                }
            }
        }
        return INSTANCE;
    }
}
