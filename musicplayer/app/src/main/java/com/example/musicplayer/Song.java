package com.example.musicplayer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Song {
    @PrimaryKey()
    private long songId;
    private String name;
    private String artists;
    private String album;
    private String genre;
    private String cover;
    private String url;

    public Song(long songId, String name, String artists, String album, String genre, String cover, String url) {
        this.songId = songId;
        this.name = name;
        this.artists = artists;
        this.album = album;
        this.genre = genre;
        this.cover = cover;
        this.url = url;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}