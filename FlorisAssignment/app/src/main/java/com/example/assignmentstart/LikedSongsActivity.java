package com.example.assignmentstart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LikedSongsActivity extends AppCompatActivity {

    List<LikedSong> listLikedSongs = new ArrayList<>();
    ArrayAdapter<LikedSong> likedSongsAdapter;
    TextView textViewNoLikedSongs;
    TextView textViewLikedSongsTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_songs);
        textViewNoLikedSongs = findViewById(R.id.textNoLikedSongs);
        textViewLikedSongsTitle = findViewById(R.id.likedSongsTitle);

        listLikedSongs = MusixRoomDatabase.getDatabase(this).liked_songsDao().getAllSongs();

        if(listLikedSongs.isEmpty()) {
            textViewNoLikedSongs.setVisibility(View.VISIBLE);
            textViewLikedSongsTitle.setVisibility(View.INVISIBLE);
        }
        ListView viewLikedSongs = findViewById(R.id.viewLikedSongs);
        likedSongsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listLikedSongs);
        viewLikedSongs.setAdapter(likedSongsAdapter);

        viewLikedSongs.setOnItemClickListener((parent, view, position, id) -> {
            onViewLikedSong(position);
        });

    }

    public void onViewLikedSong(int position) {
        LikedSong selected = listLikedSongs.get(position);
        Intent intent = new Intent(this, LyricsActivity.class);
        intent.putExtra("TRACK_ID", selected.getTrackID());
        intent.putExtra("COMMONTRACK_ID", selected.getCommonTrackID());
        intent.putExtra("TRACK_NAME", selected.getTrackName());
        intent.putExtra("ARTIST_NAME", selected.getArtist_name());
        intent.putExtra("ALBUM_NAME", selected.getAlbum_name());
        intent.putExtra("COUNTRY_CODE", selected.getCountry_code());
        startActivityForResult(intent, position);
    }

    // Refresh view on song like/unlike
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            listLikedSongs.remove(requestCode);
            likedSongsAdapter.notifyDataSetChanged();
        }
        if(listLikedSongs.isEmpty()) {
            textViewNoLikedSongs.setVisibility(View.VISIBLE);
            textViewLikedSongsTitle.setVisibility(View.INVISIBLE);
        }
    }
}
