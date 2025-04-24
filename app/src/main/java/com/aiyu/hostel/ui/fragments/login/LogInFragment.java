package com.aiyu.hostel.ui.fragments.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.core.firebase.User;
import com.aiyu.hostel.core.login.GoogleSignInUtils;
import com.aiyu.hostel.databinding.FragmentLoginBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LogInFragment extends BaseFragment {
    private static final String TAG = "LogInFragment";

    public LogInFragment() {
        super(R.layout.fragment_login);
    }

    private FragmentLoginBinding binding;

    @Inject
    GoogleSignInUtils googleSignInUtils;

    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    FirebaseInteraction firebaseInteraction;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        if (firebaseAuth.getUid() != null) {
            navigateToHome();
        }
        binding.logInGoogle.setOnClickListener(v -> googleSignInUtils.signInGoogle((user, exception) -> {
            if (exception != null) {
                Log.e(TAG, "onViewCreated: " + exception.getMessage());
                return;
            }
            if (user != null) {
                User mUser = new User(user.getDisplayName(), user.getEmail(), user.getPhoneNumber(), Objects.requireNonNull(user.getPhotoUrl()).toString(), user.getUid());
                firebaseInteraction.insertUser(mUser, e -> {
                    if (e != null) {
                        Log.e(TAG, "onViewCreated: " + e.getMessage());
                        return;
                    }
                    navigateToHome();
                });
            }
        }));
    }

    private void navigateToHome() {
        Navigation.findNavController(binding.getRoot()).navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment());
    }
}
