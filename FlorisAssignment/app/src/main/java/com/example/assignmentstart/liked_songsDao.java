package com.example.assignmentstart;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface liked_songsDao {

    @Insert
    void insert(LikedSong liked_song);

    @Query("SELECT * FROM liked_songs")
    List<LikedSong> getAllSongs();

    @Query("SELECT * FROM liked_songs WHERE commonTrackID = :commonTrackID")
    LikedSong searchSongFromPK(int commonTrackID);

    @Query("SELECT commonTrackID FROM liked_songs")
    List<Integer> getAllcommonTrackIDS();

    @Query("SELECT * FROM liked_songs ORDER BY position DESC")
    List<LikedSong> getLastSongPosition();

    @Delete
    void delete(LikedSong liked_song);
}
