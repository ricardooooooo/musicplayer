package com.example.musicplayer.retrofit;

import com.example.musicplayer.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongService {
    @GET("songs/")
    Call<List<Song>> getSongsByGenre(@Query("genre") String genre);

}

