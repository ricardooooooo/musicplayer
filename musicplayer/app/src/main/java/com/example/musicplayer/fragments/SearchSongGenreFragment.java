package com.example.musicplayer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.SearchSongAdapter;
import com.example.musicplayer.R;
import com.example.musicplayer.Song;
import com.example.musicplayer.viewmodels.SearchSongGenreViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;


public class SearchSongGenreFragment extends Fragment {

    private SearchSongGenreViewModel viewModel;
    private SearchSongAdapter adapter;
    private static final String KEY_GENRE = "genre";
    private String genre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_song_genre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        genre = this.getArguments().getString(KEY_GENRE);

        NavController navController = NavHostFragment.findNavController(SearchSongGenreFragment.this);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationViewGenre);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);


        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        navController.navigate(R.id.action_searchSongGenreFragment_to_searchFragment);
                        break;
                }

            }
        });

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        navController.navigate(R.id.action_searchSongGenreFragment_to_searchFragment);
                        break;
                }

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_about:
                        navController.navigate(R.id.action_searchSongGenreFragment_to_aboutFragment);
                        return true;
                }
                return false;
            }
        });



        this.viewModel = new ViewModelProvider(this).get(SearchSongGenreViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSongs);
        this.adapter = new SearchSongAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.adapter);
        this.viewModel.getSongsByGenre(genre).observe(getActivity(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songList) {
                SearchSongGenreFragment.this.adapter.updateList(songList);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.updateSongsByGenre(genre);
    }
}

