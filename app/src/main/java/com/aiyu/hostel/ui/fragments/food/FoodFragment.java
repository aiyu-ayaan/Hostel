package com.aiyu.hostel.ui.fragments.food;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.FoodItem;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentDetailBinding;
import com.aiyu.hostel.databinding.FragmentFoodBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.aiyu.hostel.utils.DataGenerator;

import java.util.List;
import java.util.function.BiConsumer;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodFragment extends BaseFragment {
    public FoodFragment() {
        super(R.layout.fragment_food);
    }

    private FragmentFoodBinding binding;

    @Inject
    FirebaseInteraction firebaseInteraction;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFoodBinding.bind(view);
        var adapter = new FoodAdapter();
        binding.rvFood.setAdapter(adapter);
        binding.rvFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        firebaseInteraction.getAllFood((foodItems, e) -> {
            if(e != null) {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                if (foodItems != null) {
                    adapter.submitList(foodItems);
                }
            }
        });
        adapter.setOnFoodItemClickListener(foodItem -> {
            var action = FoodFragmentDirections.actionFoodFragmentToFoodDetailsFragment(foodItem);
            Navigation.findNavController(binding.getRoot())
                    .navigate(action);
        });
    }
}
