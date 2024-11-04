package com.example.musicplayer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.musicplayer.Song;
import com.example.musicplayer.User;


@Database(entities = {Song.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SongDao getSongDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "musicplayerDB").allowMainThreadQueries().
                    build();
        }
        return INSTANCE;
    }
}
