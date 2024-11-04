package com.example.musicplayer.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicplayer.notification.CreateNotification;
import com.example.musicplayer.R;
import com.example.musicplayer.Song;
import com.example.musicplayer.viewmodels.PlayerFragmentViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PlayerFragment extends Fragment {


    private static final String KEY_ID = "songId";
    private long songId;
    private PlayerFragmentViewModel viewModel;
    private MediaPlayer mediaPlayer;
    private SeekBar playerSeekBar;
    private NotificationManager notificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        songId = this.getArguments().getLong(KEY_ID);

        this.viewModel = new ViewModelProvider(this).get(PlayerFragmentViewModel.class);

        MediaPlayer mediaPlayer = new MediaPlayer();

        ImageView imageCover = view.findViewById(R.id.imageCover);
        TextView textSongName = view.findViewById(R.id.textSongNamePlayer);
        TextView textArtist = view.findViewById(R.id.textViewArtistPlayer);
        TextView textTotalDuration = view.findViewById(R.id.textTotalDuration);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }




        this.viewModel.getSongById(songId).observe(getActivity(), new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                try {



                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }


                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(song.getUrl());
                        mediaPlayer.prepare();
                        playerSeekBar.setMax(mediaPlayer.getDuration());
                        if (!mediaPlayer.isPlaying())
                        mediaPlayer.start();


                        Glide.with(imageCover).load(song.getCover()).into(imageCover);
                        textSongName.setText(song.getName());
                        textArtist.setText(song.getArtists());

                        textTotalDuration.setText(createTimeLabel(mediaPlayer.getDuration()));

                        CreateNotification.createNotification(getActivity(), song);

                }catch (Exception e){

                }
            }
        });


        NavController navController = NavHostFragment.findNavController(PlayerFragment.this);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationViewPlayer);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_about:
                        navController.navigate(R.id.action_playerFragment_to_aboutFragment);
                        return true;
                }
                return false;
            }
        });

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        navController.navigate(R.id.action_playerFragment_to_searchFragment);
                        break;
                }

            }
        });





        TextView textCurrentTime;
        ImageView imagePlayPause, imagePrevious;

        imagePlayPause = view.findViewById(R.id.imagePlayPause);
        imagePrevious = view.findViewById(R.id.imagePrevious);
        textCurrentTime = view.findViewById(R.id.textCurrentTime);
        playerSeekBar = view.findViewById(R.id.playerSeekBar);


        imagePlayPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imagePlayPause.setImageResource(R.drawable.ic_play);

                } else {
                    imagePlayPause.setImageResource(R.drawable.ic_pause);
                    mediaPlayer.start();


                }

            }
        });


        imagePrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
            }
        });






        playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                    playerSeekBar.setProgress(progress);
                }
                textCurrentTime.setText(createTimeLabel(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mediaPlayer != null){
                    try {
                        playerSeekBar.setProgress(mediaPlayer.getCurrentPosition());

                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                }
            }
        }).start();



    }
    public String createTimeLabel(int time){
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min  + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    private void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID,
                    "Test",NotificationManager.IMPORTANCE_LOW);

            notificationManager =  getActivity().getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

}
