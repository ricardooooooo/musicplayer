package com.example.musicplayer.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.musicplayer.Repository;
public class SearchFragmentViewModel extends AndroidViewModel {


    private Repository repository;

    public SearchFragmentViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }



    public void logOut(){
        this.repository.logout();
    }
}
