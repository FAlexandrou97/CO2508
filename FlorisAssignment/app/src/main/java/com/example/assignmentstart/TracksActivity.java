package com.example.assignmentstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignmentstart.TrackListJson.Track;
import com.example.assignmentstart.TrackListJson.Track_list;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.List;

public class TracksActivity extends AppCompatActivity {
    String countryCode;
    String countryName;
    private ListView viewTopTracks;
    trackAdapter trackAdapter;
    List<Track> listTopTracks = new ArrayList<>();
    List<Integer> listLikedSongsID = MusixRoomDatabase.getDatabase(this)
            .liked_songsDao().getAllcommonTrackIDS();
    ArrayList<ItemTrack> itemTracks = new ArrayList<>();
    boolean songLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);
        viewTopTracks = findViewById(R.id.topTracks);
        trackAdapter = new trackAdapter(this, new ArrayList<>());
        viewTopTracks.setAdapter(trackAdapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            countryCode = extras.getString("country_code");
            countryName = extras.getString("country_name");
        }

        TextView textView = findViewById(R.id.tracksTitle);
        textView.setText(textView.getText() + " " + countryName);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final Request request = new StringRequest(
                Request.Method.GET,
                Globals.SERVICE_URL +
                        "chart.tracks.get?chart_name=top&page=1&page_size=20&country=" +
                        countryCode +
                        "&f_has_lyrics=1&apikey=" + Globals.API_KEY,
                stringListener, errorListener);

        requestQueue.add(request);
    }

    private Response.Listener<String> stringListener = response -> {
        ProgressBar progressBar = findViewById(R.id.progress_loader);
        progressBar.setVisibility(View.GONE);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        TrackListJson TrackListJson = gson.fromJson(response, TrackListJson.class);
        for (Track_list track_list : TrackListJson.message.body.track_list) {
            listTopTracks.add(track_list.track);
        }

        // Find liked songs and construct view accordingly
        for (Track track : listTopTracks) {
            songLiked = listLikedSongsID.contains(track.getCommontrack_id());
            itemTracks.add(new ItemTrack(track.getTrack_name(), track.getArtist_name(), songLiked));
        }

        trackAdapter = new trackAdapter(this, itemTracks);
        viewTopTracks.setAdapter(trackAdapter);
        viewTopTracks.setOnItemClickListener((parent, view, position, id) -> onViewSong(position));
    };

    private Response.ErrorListener errorListener = response -> {
        ProgressBar progressBar = findViewById(R.id.progress_loader);
        progressBar.setVisibility(View.GONE);
        TextView errorTextView = findViewById(R.id.errorText);
        errorTextView.setVisibility(View.VISIBLE);
        TextView titleTextView = findViewById(R.id.tracksTitle);
        titleTextView.setVisibility(View.INVISIBLE);
        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setVisibility(View.VISIBLE);

        if (response instanceof NetworkError || response instanceof NoConnectionError || response instanceof TimeoutError)
            errorTextView.setText("Please check your internet connection and try again!");

        if (response instanceof ServerError)
            errorTextView.setText("Something is wrong with the server! \n\n Try refreshing the page");
    };

    private void onViewSong(int position) {
        Track selected = listTopTracks.get(position);
        Intent intent = new Intent(this, LyricsActivity.class);
        intent.putExtra("TRACK_ID", selected.getTrack_id());
        intent.putExtra("COMMONTRACK_ID", selected.getCommontrack_id());
        intent.putExtra("TRACK_NAME", selected.getTrack_name());
        intent.putExtra("ARTIST_NAME", selected.getArtist_name());
        intent.putExtra("ALBUM_NAME", selected.getAlbum_name());
        intent.putExtra("COUNTRY_CODE", countryCode);
        startActivityForResult(intent, position);
    }

    // Start activity and get like/unlike song response
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    // Refresh view on song like/unlike
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            itemTracks.get(requestCode).setLiked(false);
        }
        if (resultCode == 1) {
            itemTracks.get(requestCode).setLiked(true);
        }
        trackAdapter.notifyDataSetChanged();
    }

    public void refreshActivity(View v) {
        Button refreshButton = v.findViewById(R.id.refreshButton);
        Globals.buttonAnimation(refreshButton);
        Intent intent = new Intent(this, TracksActivity.class);
        intent.putExtra("country_code", countryCode);
        intent.putExtra("country_name", countryName);
        startActivity(intent);
        finish();
    }
}
