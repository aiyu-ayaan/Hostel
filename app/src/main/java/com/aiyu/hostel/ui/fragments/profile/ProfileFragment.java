package com.aiyu.hostel.ui.fragments.profile;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentProfileBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment {
    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    private FragmentProfileBinding binding;
    @Inject
    FirebaseInteraction firebaseInteraction;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentProfileBinding.bind(view);

        binding.logoutButton.setOnClickListener(v -> {
            firebaseInteraction.logOut(unused -> {
                var action = ProfileFragmentDirections.actionProfileFragmentToLogInFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            });
        });
        firebaseInteraction.getUser((user, exception) -> {
            if (exception != null) {
                Toast.makeText(requireContext(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            if (user != null) {
                binding.relativeLayoutProfile.setVisibility(View.VISIBLE);
                binding.linearLayoutLoading.setVisibility(View.GONE);
                Glide.with(requireContext()).load(user.getProfilePic()).into(binding.imageViewProfile);
                binding.profileName.setText(user.getName());
                binding.profileEmail.setText(user.getEmail());

                if (user.getCreatedAt() != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(new Date(user.getCreatedAt()));
                    binding.memberSince.setText(formattedDate);
                } else {
                    binding.memberSince.setText("Not available");
                }
                binding.userId.setText(user.getUid());
                try {
                    PackageInfo packageInfo = requireActivity().getPackageManager().getPackageInfo(requireActivity().getPackageName(), 0);
                    String versionName = packageInfo.versionName;
                    binding.appVersion.setText(versionName);
                } catch (PackageManager.NameNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
