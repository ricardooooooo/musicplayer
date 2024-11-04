package com.example.musicplayer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musicplayer.R;
import com.example.musicplayer.User;
import com.example.musicplayer.viewmodels.LoginFragmentViewModel;

public class RegisterFragment extends Fragment {

    private LoginFragmentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);

        EditText editTextEmailRegister = view.findViewById(R.id.editTextEmailRegister);
        EditText editTextNameRegister = view.findViewById(R.id.editTextNameRegister);
        EditText editTextPasswordRegister = view.findViewById(R.id.editTextPasswordRegister);

        Button btnRegister = view.findViewById(R.id.btnRegister);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = editTextNameRegister.getText().toString();
                    String email = editTextEmailRegister.getText().toString();
                    String password = editTextPasswordRegister.getText().toString();

                    if (!name.isEmpty() && !email.isEmpty()  && !password.isEmpty()){
                        User user = new User(0,name, email, password);
                        viewModel.addUser(user);
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                    }
                }
            });
        }

}