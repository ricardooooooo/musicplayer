package com.example.musicplayer.retrofit;

import com.example.musicplayer.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("users/")
    Call<List<User>> getUser(@Query("email") String email, @Query("password") String password);

    @POST("users/")
    Call<Boolean>setUser(@Body User user);

}
