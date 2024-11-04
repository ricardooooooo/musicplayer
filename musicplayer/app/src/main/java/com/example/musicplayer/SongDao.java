package com.example.musicplayer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Query("SELECT * FROM Song WHERE genre = :genre")
    LiveData<List<Song>> getAllGenreSongs(String genre);

    @Query("SELECT * FROM Song WHERE songId = :songId")
    LiveData<Song> getSongById(long songId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<Song> songList);


}
