package com.example.musicplayer.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.musicplayer.Repository;
import com.example.musicplayer.User;

public class LoginFragmentViewModel extends AndroidViewModel {

    private Repository repository;

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public LiveData<User> getUser() {
        return this.repository.getLoggedInUser();
    }

    public void tryToLogInUser(String email, String password) {
        this.repository.tryToLoginUser(email, password);
    }
    public void addUser(User user) {
        this.repository.addUser(user);
    }

}
