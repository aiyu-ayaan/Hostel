package com.aiyu.hostel.ui.fragments.food.history;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentOrderFoodBinding;
import com.aiyu.hostel.utils.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderHistoryFragment extends BaseFragment {
    public OrderHistoryFragment() {
        super(R.layout.fragment_order_food);
    }

    @Inject
    FirebaseInteraction firebaseInteraction;

    private FragmentOrderFoodBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentOrderFoodBinding.bind(view);
        binding.topbar.setNavigationOnClickListener(v -> {
            navigateUp();
        });
        var adapter = new OrdersAdapter();
        binding.rvOrders.setAdapter(adapter);
        binding.rvOrders.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvOrders.setHasFixedSize(true);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.tvEmptyOrders.setVisibility(View.GONE);
        binding.rvOrders.setVisibility(View.GONE);
        firebaseInteraction.getAllBuyFood((buyFoods, e) -> {
            if (e != null) {
                showError(e.getLocalizedMessage());
            } else {
                binding.progressBar.setVisibility(View.GONE);
                if (buyFoods != null) {
                    if (buyFoods.isEmpty()) {
                        binding.tvEmptyOrders.setVisibility(View.VISIBLE);
                        binding.rvOrders.setVisibility(View.GONE);
                    } else {
                        binding.tvEmptyOrders.setVisibility(View.GONE);
                        binding.rvOrders.setVisibility(View.VISIBLE);
                        adapter.submitList(buyFoods);
                    }
                }
            }
        });
    }

    private void navigateUp() {
        Navigation.findNavController(binding.getRoot()).navigateUp();
    }

    private void showError(String error) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
    }

}
