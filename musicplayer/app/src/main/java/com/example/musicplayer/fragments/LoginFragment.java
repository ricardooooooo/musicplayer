package com.example.musicplayer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.viewmodels.LoginFragmentViewModel;
import com.example.musicplayer.R;
import com.example.musicplayer.User;

public class LoginFragment extends Fragment {

    private LoginFragmentViewModel viewModel;
    private boolean tryingToLogIn = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.btnLogin);

        this.viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);
        NavController navController = NavHostFragment.findNavController(LoginFragment.this);



        this.viewModel.getUser().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user == null) {
                    if (tryingToLogIn) {
                        Toast.makeText(getActivity(), "Dados errados!", Toast.LENGTH_LONG).show();

                    }
                } else {
                    navController.navigate(R.id.action_loginFragment_to_searchFragment);
                }
            }
        });

        EditText insertEmail, insertPassword;
        TextView textRegister;
        insertEmail = view.findViewById(R.id.editTextEmail);
        insertPassword = view.findViewById(R.id.editTextPassword);
        textRegister = view.findViewById(R.id.textRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = insertEmail.getText().toString();
                password = insertPassword.getText().toString();

                tryingToLogIn = true;
                viewModel.tryToLogInUser(email, password);
            }
        });


        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);

            }
        });


    }
}