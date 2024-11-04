package com.example.musicplayer.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.musicplayer.Repository;
import com.example.musicplayer.Song;

import java.util.List;

public class SearchSongGenreViewModel extends AndroidViewModel {

    private Repository repository;

    public SearchSongGenreViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

   // public LiveData<List<Song>> getHipHopSongs() {
     //   return this.repository.getHipHopSongs();
   // }


    public void updateSongsByGenre(String genre) {
        this.repository.updateSongsByGenre(genre);
    }

    public LiveData<List<Song>> getSongsByGenre(String genre) {
        return this.repository.getSongsByGenre(genre);
    }



    // public void updateList() {
  //      this.repository.updateList();
  //  }

}
