package com.example.assignmentstart;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface countriesDao {

    @Insert
    void insert(country country);

    @Update
    void update(country country);

    @Query("SELECT * FROM countries")
    List<country> getAllCountries();

    @Query("SELECT * FROM countries WHERE favourite=1")
    List<country> getFavCountries();

    @Delete
    void delete(country country);
}
