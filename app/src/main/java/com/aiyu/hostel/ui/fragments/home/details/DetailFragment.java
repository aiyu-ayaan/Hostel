package com.aiyu.hostel.ui.fragments.home.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.Amenity;
import com.aiyu.hostel.core.data.Hostel;
import com.aiyu.hostel.databinding.FragmentDetailBinding;
import com.aiyu.hostel.utils.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends BaseFragment {

    private DetailFragmentArgs args;

    public DetailFragment() {
        super(R.layout.fragment_detail);
    }

    private FragmentDetailBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args = DetailFragmentArgs.fromBundle(getArguments());
        binding = FragmentDetailBinding.bind(view);
        var hostel = args.getHostel();
        setupUI(hostel);
    }

    @SuppressLint("DefaultLocale")
    private void setupUI(Hostel hostel) {
        // Set hostel details to UI components
        binding.tvHostelName.setText(hostel.getName());
        binding.collapsingToolbar.setTitle(hostel.getName());
        binding.tvHostelLocation.setText(hostel.getLocation());
        binding.rbHostelRating.setRating(hostel.getRating());
        binding.tvRatingCount.setText(String.format("(%d reviews)", hostel.getReviewCount()));
        binding.tvHostelPrice.setText(hostel.getPricePerMonth());
        binding.tvDescription.setText(hostel.getDescription());
        ImagePagerAdapter adapter = new ImagePagerAdapter(requireContext(), hostel.getImageUrls());
        binding.vpHostelImages.setAdapter(adapter);

        binding.toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigateUp();
        });
//        enable back button on toolbar
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);

        // Setup other UI components
        setupAmenities(hostel);
        setupPolicies(hostel);
        setupAvailability(hostel);
        setupImageSlider(hostel);
    }

    private void setupImageSlider(Hostel hostel) {
        var adapter = new RoomOptionAdapter();
        adapter.submitList(hostel.getRoomOptions());
        adapter.setClickListener(roomOption -> {
        });
        binding.rvRoomOptions.setAdapter(adapter);
        binding.rvRoomOptions.setVisibility(View.VISIBLE);
        binding.rvRoomOptions.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.rvRoomOptions.setHasFixedSize(true);
    }

    @SuppressLint("SetTextI18n")
    private void setupAvailability(Hostel hostel) {
        int availableBeds = hostel.getAvailableBeds();
        if (availableBeds > 0) {
            binding.tvAvailability.setText(availableBeds + " beds available");
            binding.btnBookNow.setEnabled(true);
        } else {
            binding.tvAvailability.setText("Currently unavailable");
            binding.btnBookNow.setEnabled(false);
        }
    }

    private void setupPolicies(Hostel hostel) {
        if (hostel.getPolicies() != null && !hostel.getPolicies().isEmpty()) {
            StringBuilder policiesText = new StringBuilder();
            for (String policy : hostel.getPolicies()) {
                policiesText.append("• ").append(policy).append("\n");
            }
            binding.tvPolicies.setText(policiesText.toString().trim());
        }
    }

    private void setupAmenities(Hostel hostel) {
        binding.chipWifi.setVisibility(hostel.hasAmenity(Amenity.WIFI) ? View.VISIBLE : View.GONE);
        binding.chipAc.setVisibility(hostel.hasAmenity(Amenity.AC) ? View.VISIBLE : View.GONE);
        binding.chipFood.setVisibility(hostel.hasAmenity(Amenity.FOOD) ? View.VISIBLE : View.GONE);
        binding.chipLaundry.setVisibility(hostel.hasAmenity(Amenity.LAUNDRY) ? View.VISIBLE : View.GONE);
        binding.chipGym.setVisibility(hostel.hasAmenity(Amenity.GYM) ? View.VISIBLE : View.GONE);
        binding.chipStudyRoom.setVisibility(hostel.hasAmenity(Amenity.STUDY_ROOM) ? View.VISIBLE : View.GONE);
        binding.chipParking.setVisibility(hostel.hasAmenity(Amenity.PARKING) ? View.VISIBLE : View.GONE);
    }

    private void showRoomSelectedMessage(Hostel.RoomOption roomOption) {
        // Show a message when a room is selected
        Toast.makeText(requireContext(),
                "Selected: " + roomOption.getType(),
                Toast.LENGTH_SHORT).show();
    }
}
