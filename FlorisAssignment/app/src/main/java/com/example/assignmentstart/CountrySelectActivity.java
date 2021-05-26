package com.example.assignmentstart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CountrySelectActivity extends AppCompatActivity {

    ListView viewAllCountries;
    CountryAdapter countryAdapter;
    List<country> listAllCountries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_select);

        // Get all countries from DB
        listAllCountries = MusixRoomDatabase
                .getDatabase(this)
                .countriesDao()
                .getAllCountries();
        viewAllCountries = findViewById(R.id.allCountries);

        ArrayList<ItemCountry> itemCountries = new ArrayList<>();
        for (country country : listAllCountries) {
            itemCountries.add(new ItemCountry(R.drawable.button_favorite, country.getName(), country.getFavourite() != 0));
        }

        countryAdapter = new CountryAdapter(this, itemCountries);
        viewAllCountries.setAdapter(countryAdapter);
        registerForContextMenu(viewAllCountries);

        // Add favourite countries
        viewAllCountries.setOnItemClickListener((parent, view, position, id) -> {
            ToggleButton buttonFavorite = view.findViewById(R.id.button_favorite);
            buttonFavorite.performClick();
            Globals.buttonAnimation(buttonFavorite);
            onFavoriteCountry(position);
        });
    }

    public void onFavoriteCountry(int position) {
        country selected = listAllCountries.get(position);
        // Favorite-UnFavorite functionality
        if (selected.getFavourite() == 0)
            selected.setFavourite(1);
        else
            selected.setFavourite(0);

        MusixRoomDatabase.getDatabase(this).countriesDao().update(selected);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.country_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        country currentCountry = listAllCountries.get(info.position);
        Log.e("wow" , Integer.toString(info.position));
        switch (item.getItemId()) {
            case R.id.countryWiki:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentCountry.getWikipedia()));
                startActivity(browserIntent);
                break;
            case R.id.countryMaps:
                Uri gMapsIntentUri = Uri.parse("geo:" + currentCountry.getLat() + "," + currentCountry.getLng());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gMapsIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}
