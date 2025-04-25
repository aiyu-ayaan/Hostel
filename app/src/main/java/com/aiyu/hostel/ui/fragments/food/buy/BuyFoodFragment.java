package com.aiyu.hostel.ui.fragments.food.buy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.BuyFood;
import com.aiyu.hostel.core.data.Hostel;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentBuyFoodBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BuyFoodFragment extends BaseFragment {
    public BuyFoodFragment() {
        super(R.layout.fragment_buy_food);
    }

    private FragmentBuyFoodBinding binding;
    private BuyFoodFragmentArgs args;

    @Inject
    FirebaseInteraction firebaseInteraction;

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBuyFoodBinding.bind(view);
        args = BuyFoodFragmentArgs.fromBundle(getArguments());
        var foodItem = args.getFood();
        binding.tvFoodName.setText(foodItem.getName());
        binding.topBar.setNavigationOnClickListener(v -> {
            navigateUp();
        });
        binding.tvPrice.setText("₹ " + foodItem.getPrice());
        binding.tvFoodDescription.setText(foodItem.getDescription());
        binding.tvPrepTime.setText("Prep Time: " + foodItem.getPreparationTimeMinutes() + " mins");
        Glide.with(binding.getRoot())
                .load(foodItem.getImageUrl())
                .error(R.drawable.ic_food_base)
                .into(binding.ivFoodImage);
        firebaseInteraction.getAllHostels((hostels, e) -> {
            if (e != null) {
                showError(e.getLocalizedMessage());
                return;
            }
            if (hostels != null) {
                var hostelNames = hostels.stream().map(Hostel::getName);
                var hostelNamesArray = hostelNames.toArray(String[]::new);
                var adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, hostelNamesArray);
                binding.tvDropDownHostel.setAdapter(adapter);
            }
        });
        binding.btnPlaceOrder.setOnClickListener(v -> {
            boolean isValid = true;
            String selectedHostel = binding.tvDropDownHostel.getText().toString();
            String roomNumber = binding.tilRoomNumber.getEditText().getText().toString();
            if (selectedHostel.isEmpty() || selectedHostel.equals(requireContext().getString(R.string.no_selected))) {
                // Show error for empty hostel
                binding.tvDropDownHostel.setError("Please select a hostel");
                isValid = false;
            } else {
                binding.tvDropDownHostel.setError(null);
            }

            if (roomNumber.isEmpty()) {
                binding.tilRoomNumber.setError("Room number is required");
                isValid = false;
            } else {
                binding.tilRoomNumber.setError(null);
            }
            if (!isValid) {
                return;
            }
            BuyFood order = new BuyFood(
                    "",
                    foodItem,
                    selectedHostel,
                    roomNumber,
                    System.currentTimeMillis()
            );
            firebaseInteraction.insertBuyFood(order, e1 -> {
                if (e1 != null) {
                    showError(e1.getLocalizedMessage());
                    return;
                }
                showError("Order placed successfully");
                navigateUp();
            });
        });
    }

    private void navigateUp() {
        Navigation.findNavController(binding.getRoot())
                .navigate(BuyFoodFragmentDirections.actionBuyFoodFragmentToFoodFragment());
    }

    private void showError(String localizedMessage) {
        Toast.makeText(requireContext(), localizedMessage, Toast.LENGTH_SHORT).show();
    }

}
