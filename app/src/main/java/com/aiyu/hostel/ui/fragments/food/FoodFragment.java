package com.aiyu.hostel.ui.fragments.food;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aiyu.hostel.R;
import com.aiyu.hostel.databinding.FragmentDetailBinding;
import com.aiyu.hostel.databinding.FragmentFoodBinding;
import com.aiyu.hostel.utils.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodFragment extends BaseFragment {
    public FoodFragment() {
        super(R.layout.fragment_food);
    }

    private FragmentFoodBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFoodBinding.bind(view);
    }
}
