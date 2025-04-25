package com.aiyu.hostel.ui.fragments.home.details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
        binding.fabContact.setOnClickListener(v -> {
            openContact(hostel);
        });
        binding.btnBookNow.setOnClickListener(v -> {
            openContact(hostel);
        });
    }

    private void openContact(Hostel hostel) {
        String contactInfo = hostel.getContactInfo();

        if (contactInfo != null && !contactInfo.isEmpty()) {
            // Check if it's a phone number (simple validation for demonstration)
            if (contactInfo.matches("^[+]?[0-9\\s-()]{8,}$")) {
                // It appears to be a phone number, open dialer
                openPhoneDialer(contactInfo);
            }
            // Check if it's an email
            else if (contactInfo.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                // It appears to be an email, open email app
                openEmailApp(contactInfo);
            }
            // Not recognized as phone or email
            else {
                Toast.makeText(requireContext(), "Invalid contact information", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "No contact information available", Toast.LENGTH_SHORT).show();
        }
    }

    private void openPhoneDialer(String phoneNumber) {
        try {
            // Create the intent with ACTION_DIAL (this doesn't require permission)
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);

            // Format the phone number URI
            Uri uri = Uri.parse("tel:" + phoneNumber);

            // Set the data for the intent
            dialIntent.setData(uri);

            // Start the activity
            startActivity(dialIntent);
        } catch (Exception e) {
            // Handle any exceptions (e.g., if there's no dialer app)
            Toast.makeText(requireContext(), "Unable to open dialer", Toast.LENGTH_SHORT).show();

        }
    }

    private void openEmailApp(String emailAddress) {
        try {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + emailAddress));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry about Hostel");
            startActivity(emailIntent);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Unable to open email app", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupImageSlider(Hostel hostel) {
        var adapter = new RoomOptionAdapter();
        adapter.submitList(hostel.getRoomOptions());
        adapter.setClickListener(roomOption -> {
        });
        binding.rvRoomOptions.setAdapter(adapter);
        binding.rvRoomOptions.setVisibility(View.VISIBLE);
        binding.rvRoomOptions.setLayoutManager(new LinearLayoutManager(requireContext()));
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
