package com.aiyu.hostel.ui.fragments.food.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.aiyu.hostel.R;
import com.aiyu.hostel.databinding.FragmentFoodDetailsBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.bumptech.glide.Glide;

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
//        enable back button on toolbar
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        binding.tvFoodDetailName.setText(food.getName());
        binding.tvFoodDetailDescription.setText(food.getDescription());
        binding.tvFoodDetailPrice.setText(String.valueOf(food.getPrice()));
        binding.tvDetailPreparationTime.setText(food.getPreparationTimeMinutes() + " min");
        binding.rbDetailFoodRating.setRating(food.getRating());
        binding.tvRatingCount.setText(String.valueOf(food.getRatingCount()));
        Glide.with(binding.getRoot())
                .load(food.getImageUrl())
                .error(R.drawable.ic_food_base)
                .into(binding.ivFoodDetailImage);
        binding.btOrder.setOnClickListener(v -> {
            var action = FoodDetailsFragmentDirections.actionFoodDetailsFragmentToBuyFoodFragment(food);
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });

    }
}
