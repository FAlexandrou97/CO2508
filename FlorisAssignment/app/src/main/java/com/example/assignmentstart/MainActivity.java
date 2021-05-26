package com.example.assignmentstart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView viewFavoriteCountries;
    List<country> listFavCountries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("Preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        boolean isNightModeEnabled = mPrefs.getBoolean("DARK_MODE", false);
        if (isNightModeEnabled)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        Switch switchDarkMode = findViewById(R.id.switchDarkMode);

        if (isNightModeEnabled)
            switchDarkMode.setChecked(true);

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Intent intent = new Intent(this, MainActivity.class);
            if (isChecked) {
                editor.putBoolean("DARK_MODE", true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                editor.putBoolean("DARK_MODE", false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            editor.apply();
            startActivity(intent);
            finish();
        });

        viewFavoriteCountries = findViewById(R.id.favoriteCountries);
        ArrayAdapter<country> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listFavCountries);



        viewFavoriteCountries.setAdapter(arrayAdapter);


        viewFavoriteCountries.setOnItemClickListener((parent, view, position, id) -> onSelectCountry(position));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update view
        listFavCountries = MusixRoomDatabase
                .getDatabase(this)
                .countriesDao()
                .getFavCountries();
        final ListAdapter countriesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listFavCountries);
        viewFavoriteCountries.setAdapter(countriesAdapter);

    }

    public void addCountry(View v) {
        Intent intent = new Intent(this, CountrySelectActivity.class);
        startActivity(intent);
    }

    public void showLikedSongs(View v) {
        Intent intent = new Intent(this, LikedSongsActivity.class);
        startActivity(intent);
    }

    private void onSelectCountry(int position) {
        Intent intent = new Intent(this, TracksActivity.class);
        intent.putExtra("country_code", listFavCountries.get(position).getCode());
        intent.putExtra("country_name", listFavCountries.get(position).getName());
        startActivity(intent);
    }
}
