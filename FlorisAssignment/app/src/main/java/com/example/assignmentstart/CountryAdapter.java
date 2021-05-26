package com.example.assignmentstart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<ItemCountry> {

    private Context context;
    private List<ItemCountry> countriesList;

    public CountryAdapter(@NonNull Context context, ArrayList<ItemCountry> countriesList) {
        super(context, 0, countriesList);
        this.context = context;
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.activity_item_country, parent, false);

        ItemCountry currentCountry = countriesList.get(position);
        TextView countryName = listItem.findViewById(R.id.countryName);
        ToggleButton buttonFavorite = listItem.findViewById(R.id.button_favorite);
        buttonFavorite.setChecked(currentCountry.isFavorite());
        countryName.setText(currentCountry.getCountryName());

        return listItem;
    }
}
