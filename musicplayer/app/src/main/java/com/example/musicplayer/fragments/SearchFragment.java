package com.example.musicplayer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.musicplayer.R;
import com.example.musicplayer.viewmodels.LoginFragmentViewModel;
import com.example.musicplayer.viewmodels.SearchFragmentViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class SearchFragment extends Fragment {
    private static final String KEY_GENRE = "genre";
    private SearchFragmentViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        this.viewModel = new ViewModelProvider(this).get(SearchFragmentViewModel.class);


        NavController navController = NavHostFragment.findNavController(SearchFragment.this);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationViewSearch);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_about:
                        navController.navigate(R.id.action_searchFragment_to_aboutFragment);
                        return true;
                }
                return false;
            }
        });




    Bundle bundle = new Bundle();

    Button btnHipHop = view.findViewById(R.id.btnHipHop);
    btnHipHop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Bundle bundle = new Bundle();
            bundle.putString(KEY_GENRE,"Hip-hop"); // Put anything what you want
            Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_searchSongGenreFragment, bundle);
        }
    });

        Button btnEDM = view.findViewById(R.id.btnEDM);
        btnEDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString(KEY_GENRE,"EDM"); // Put anything what you want
                Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_searchSongGenreFragment, bundle);
            }
        });

        Button btnRap = view.findViewById(R.id.btnRap);
        btnRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString(KEY_GENRE,"Rap"); // Put anything what you want
                Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_searchSongGenreFragment, bundle);
            }
        });

        Button btnIndieRock = view.findViewById(R.id.btnIndieRock);
        btnIndieRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString(KEY_GENRE,"Indie rock"); // Put anything what you want
                Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_searchSongGenreFragment, bundle);
            }
        });

        Button btnPop = view.findViewById(R.id.btnPop);
        btnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString(KEY_GENRE,"Pop"); // Put anything what you want
                Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_searchSongGenreFragment, bundle);
            }
        });








    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuLogout) {


            viewModel.logOut();
            NavController navController = NavHostFragment.findNavController(SearchFragment.this);
            navController.navigate(R.id.action_searchFragment_to_loginFragment);
        }

        return super.onOptionsItemSelected(item);
    }
}