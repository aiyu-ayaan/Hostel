package com.aiyu.hostel.ui.fragments.food.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.aiyu.hostel.R;
import com.aiyu.hostel.databinding.FragmentFoodDetailsBinding;
import com.aiyu.hostel.utils.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodDetailsFragment extends BaseFragment {

    public FoodDetailsFragment() {
        super(R.layout.fragment_food_details);
    }

    private FragmentFoodDetailsBinding binding;
    private FoodDetailsFragmentArgs args;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFoodDetailsBinding.bind(view);
        args = FoodDetailsFragmentArgs.fromBundle(getArguments());
        var food = args.getFood();
        binding.toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigateUp();
        });
        binding.tvFoodDetailName.setText(food.getName());
        binding.tvFoodDetailDescription.setText(food.getDescription());
        binding.tvFoodDetailPrice.setText(String.valueOf(food.getPrice()));
        binding.tvDetailPreparationTime.setText(food.getPreparationTimeMinutes());
        binding.rbDetailFoodRating.setRating(food.getRating());
        binding.tvRatingCount.setText(String.valueOf(food.getRatingCount()));
        binding.btOrder.setOnClickListener(v -> {

        });

    }
}
