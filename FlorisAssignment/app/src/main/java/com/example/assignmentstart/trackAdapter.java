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

public class trackAdapter extends ArrayAdapter<ItemTrack> {

    private Context context;
    private List<ItemTrack> tracksList;

    public trackAdapter(@NonNull Context context, @NonNull ArrayList<ItemTrack> tracksList) {
        super(context, 0, tracksList);
        this.context = context;
        this.tracksList = tracksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.activity_item_track, parent, false);

        ItemTrack currentTrack = tracksList.get(position);
        TextView trackRank = listItem.findViewById(R.id.trackRank);
        TextView trackName = listItem.findViewById(R.id.trackName);
        TextView artistName = listItem.findViewById(R.id.artistName);
        ToggleButton buttonLike = listItem.findViewById(R.id.button_track_liked);
        buttonLike.setVisibility(View.INVISIBLE);
        if (currentTrack.isLiked()) {
            buttonLike.setVisibility(View.VISIBLE);
            buttonLike.getBackground().mutate().setAlpha(255);
        }
        trackName.setText(currentTrack.getTrackName());
        artistName.setText(currentTrack.getArtistName());
        trackRank.setText(Integer.toString(position + 1));

        return listItem;
    }
}
