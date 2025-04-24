package com.aiyu.hostel.ui.fragments.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aiyu.hostel.R;
import com.aiyu.hostel.databinding.FragmentDetailBinding;
import com.aiyu.hostel.utils.BaseFragment;

public class HomeFragment extends BaseFragment {
    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    private FragmentDetailBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentDetailBinding.bind(view);
    }
}
