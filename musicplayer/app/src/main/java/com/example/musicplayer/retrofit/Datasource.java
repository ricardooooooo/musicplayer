package com.example.musicplayer.retrofit;



import com.example.musicplayer.retrofit.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Datasource {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/ricardooooooo/musicplayer/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static UserService getUserService() {
        return retrofit.create(UserService.class);
    }
    public static SongService getSongService() {
        return retrofit.create(SongService.class);
    }
}