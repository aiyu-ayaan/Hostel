package com.aiyu.hostel.ui.fragments.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.Hostel;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentHomeBinding;
import com.aiyu.hostel.utils.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment {
    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    private FragmentHomeBinding binding;

    @Inject
    FirebaseInteraction firebaseInteraction;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);
        var adapter = new HomeAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setHasFixedSize(true);
        adapter.setOnHostelClickListener(new HomeAdapter.OnHostelClickListener() {
            @Override
            public void onHostelClick(Hostel hostel) {
                navigateToDetails(hostel);
            }

            @Override
            public void onViewDetailsClick(Hostel hostel) {
                navigateToDetails(hostel);
            }
        });
        firebaseInteraction.getAllHostels((hostels, e) -> {
            if (e != null) {
                // Handle error
                return;
            }
            if (hostels != null) {
                adapter.submitList(hostels);
            }
        });
    }

    private void navigateToDetails(Hostel hostel) {
        var action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(hostel);
        Navigation.findNavController(binding.getRoot())
                .navigate(action);
    }
}
