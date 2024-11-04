package com.example.musicplayer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SearchSongAdapter extends RecyclerView.Adapter<SearchSongAdapter.ViewHolder>{

    private final Context context;
    private List<Song> songList = new ArrayList<>();
    private static final String KEY_ID = "songId";


    public SearchSongAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SearchSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSongAdapter.ViewHolder holder, int position) {
        Song song = this.songList.get(position);

        holder.textViewName.setText(song.getName());
        holder.textViewArtist.setText(song.getArtists());
        holder.textViewAlbum.setText(song.getAlbum());
        Glide.with(context).load(song.getCover()).into(holder.imageView);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong(KEY_ID,song.getSongId()); // Put anything what you want
                Navigation.findNavController(view).navigate(R.id.action_searchSongGenreFragment_to_playerFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.songList.size();
    }

    public void updateList(List<Song> newList) {
        this.songList = newList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView textViewName,textViewArtist,textViewAlbum;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;
            this.textViewName = this.root.findViewById(R.id.textSongName);
            this.textViewArtist = this.root.findViewById(R.id.textViewArtistPlayer);
            this.textViewAlbum = this.root.findViewById(R.id.textViewAlbum);
            this.imageView = this.root.findViewById(R.id.imageView);
        }
    }
}
