package com.example.assignmentstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class LyricsActivity extends AppCompatActivity {
    int trackID;
    int commonTrackID;
    int newTrackPosition = 0;
    String trackName;
    String artistName;
    String albumName;
    String countryCode;
    boolean songLiked;
    List<Integer> listAllTrackIDS = MusixRoomDatabase.getDatabase(this).liked_songsDao().getAllcommonTrackIDS();

    TextView lyricsTitle;
    TextView lyricsBody;
    TextView copyright;
    ToggleButton likeButton;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            trackID = extras.getInt("TRACK_ID");
            commonTrackID = extras.getInt("COMMONTRACK_ID");
            trackName = extras.getString("TRACK_NAME");
            artistName = extras.getString("ARTIST_NAME");
            albumName = extras.getString("ALBUM_NAME");
            countryCode = extras.getString("COUNTRY_CODE");
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final Request request = new StringRequest(
                Request.Method.GET,
                Globals.SERVICE_URL +
                        "track.lyrics.get?track_id=" +
                        trackID + "&apikey=" + Globals.API_KEY,
                stringListener, errorListener);

        requestQueue.add(request);

        lyricsTitle = findViewById(R.id.lyricsTitle);
        lyricsTitle.setText(trackName + " by " + artistName + "\n Album: " + albumName);


        likeButton = findViewById(R.id.button_like);
        if (listAllTrackIDS.contains(commonTrackID)) {
            songLiked = true;
            likeButton.getBackground().mutate().setAlpha(255);
        } else
            likeButton.getBackground().mutate().setAlpha(76);

        likeButton.setOnClickListener(v -> {

            // Notify last activity that song has been liked/unliked
            Intent resultIntent = new Intent();

            if (!songLiked) {
                Date c = Calendar.getInstance().getTime();
                String formattedDate = df.format(c);
                List<LikedSong> listGetPosition = MusixRoomDatabase.getDatabase(this).liked_songsDao().getLastSongPosition();
                if (listGetPosition.size() != 0)
                    newTrackPosition = listGetPosition.get(0).getPosition() + 1;
                LikedSong liked_song = new LikedSong(trackID, commonTrackID, trackName, newTrackPosition, countryCode, formattedDate, artistName, albumName);
                likeButton.getBackground().setAlpha(255);
                Globals.buttonAnimation(likeButton);
                MusixRoomDatabase.getDatabase(this)
                        .liked_songsDao().insert(liked_song);
                songLiked = true;
                setResult(LyricsActivity.RESULT_FIRST_USER, resultIntent);
            } else {
                LikedSong songToBeRemoved = MusixRoomDatabase.getDatabase(this).liked_songsDao().searchSongFromPK(commonTrackID);
                MusixRoomDatabase.getDatabase(this).liked_songsDao().delete(songToBeRemoved);
                likeButton.getBackground().setAlpha(76);
                Globals.buttonAnimation(likeButton);
                songLiked = false;
                setResult(LyricsActivity.RESULT_OK, resultIntent);
            }
        });
    }

    private Response.Listener<String> stringListener = response -> {
        ProgressBar progressBar = findViewById(R.id.lyricsProgress_loader);
        progressBar.setVisibility(View.GONE);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LyricsJson lyricsJson = gson.fromJson(response, LyricsJson.class);
        lyricsBody = findViewById(R.id.lyricsBody);
        lyricsBody.setText(lyricsJson.toString());

        copyright = findViewById(R.id.copyright);
        copyright.setText(lyricsJson.message.body.lyrics.lyrics_copyright);
    };

    private Response.ErrorListener errorListener = response -> {
        ProgressBar progressBar = findViewById(R.id.lyricsProgress_loader);
        progressBar.setVisibility(View.GONE);
        TextView errorTextView = findViewById(R.id.lyricsErrorText);
        errorTextView.setVisibility(View.VISIBLE);
        TextView titleTextView = findViewById(R.id.lyricsTitle);
        titleTextView.setVisibility(View.INVISIBLE);
        ToggleButton likeButton = findViewById(R.id.button_like);
        likeButton.setVisibility(View.INVISIBLE);


        if (response instanceof NetworkError || response instanceof NoConnectionError || response instanceof TimeoutError)
            errorTextView.setText("Please check your internet connection and try again!");

        if (response instanceof ServerError)
            errorTextView.setText("Something is wrong with the server! \n\n Please go back and try again!");
    };
}
