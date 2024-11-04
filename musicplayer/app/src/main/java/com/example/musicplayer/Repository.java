package com.example.musicplayer;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.musicplayer.retrofit.Datasource;
import com.example.musicplayer.retrofit.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Context context;
    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private User user;
    private SongDao songDao;



    public Repository(Context context) {
        this.context = context;
        this.songDao = AppDatabase.getInstance(context).getSongDao();
    }


    public void addUser(User user){
        UserService service = Datasource.getUserService();
        service.setUser(user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean result = response.body();
                } else {
                    // Resposta mal sucedida
                    // Snakbar
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void updateSongsByGenre(String genre){
        Datasource.getSongService().getSongsByGenre(genre).enqueue(new Callback <List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                if (response.isSuccessful()) {
                    List<Song> songList = response.body();
                    if (songList.size() > 0) {
                        // Need a new Thread because we are on UI Thread
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // Update list on Room Table
                               songDao.add(songList);
                            }
                        }).start();
                    }
                } else {
                    // Resposta mal sucedida
                    // Snakbar
                }
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



    public void tryToLoginUser(String email, String password) {
        Datasource.getUserService().getUser(email, password).enqueue(new Callback <List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> userList = response.body();
                    if (userList.size() > 0) {
                        user = userList.get(0);
                        if (null != user) {
                            SessionManager.saveSession(Repository.this.context, user);
                            userMutableLiveData.postValue(user);
                        }
                    }else{
                        userMutableLiveData.postValue(null);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                userMutableLiveData.postValue(null);
            }
        });

    }

    public LiveData<User> getLoggedInUser() {
        this.getUserInternal();
        return userMutableLiveData;
    }

    private void getUserInternal() {
        User user = SessionManager.getActiveSession(this.context);
        userMutableLiveData.postValue(user);
    }

    public void logout() {
        SessionManager.logout(this.context);
    }


    public LiveData<List<Song>> getSongsByGenre(String genre) {
        return this.songDao.getAllGenreSongs(genre);
    }

    public LiveData<Song> getSongById(long songId) {
        return this.songDao.getSongById(songId);
    }
}
