package com.aiyu.hostel.ui.fragments.ticket.add;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.Priority;
import com.aiyu.hostel.core.data.Ticket;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentAddTicketBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddTicketFragment extends BaseFragment {

    private FragmentAddTicketBinding binding;
    private String[] categories = {"Maintenance", "Housekeeping", "Security", "IT Support", "Other"};

    @Inject
    FirebaseInteraction firebaseInteraction;

    @Inject
    FirebaseAuth firebaseAuth;


    public AddTicketFragment() {
        super(R.layout.fragment_add_ticket);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentAddTicketBinding.bind(view);

        setupToolbar();
        setupCategoryDropdown();
        setupSubmitButton();
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v ->
                showSuccessAndNavigateBack());
    }

    private void setupCategoryDropdown() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                categories
        );
        binding.dropdownCategory.setAdapter(categoryAdapter);
    }

    private void setupSubmitButton() {
        binding.btnSubmitTicket.setOnClickListener(v -> validateAndSubmitTicket());
    }

    private void validateAndSubmitTicket() {
        // Validate title
        String title = binding.etTicketTitle.getText().toString().trim();
        if (title.isEmpty()) {
            binding.tilTicketTitle.setError("Title is required");
            binding.tilTicketTitle.requestFocus();
            return;
        } else {
            binding.tilTicketTitle.setError(null);
        }

        // Validate description
        String description = binding.etTicketDescription.getText().toString().trim();
        if (description.isEmpty()) {
            binding.tilTicketDescription.setError("Description is required");
            binding.tilTicketDescription.requestFocus();
            return;
        } else {
            binding.tilTicketDescription.setError(null);
        }

        // Validate room number
        String roomNumber = binding.etRoomNumber.getText().toString().trim();
        if (roomNumber.isEmpty()) {
            binding.tilRoomNumber.setError("Room number is required");
            binding.tilRoomNumber.requestFocus();
            return;
        } else {
            binding.tilRoomNumber.setError(null);
        }

        // Get category
        String category = binding.dropdownCategory.getText().toString();

        // Get priority
        Priority priority;
        int priorityId = binding.rgPriority.getCheckedRadioButtonId();
        if (priorityId == binding.rbPriorityLow.getId()) {
            priority = Priority.LOW;
        } else if (priorityId == binding.rbPriorityHigh.getId()) {
            priority = Priority.HIGH;
        } else {
            priority = Priority.MEDIUM;
        }

        // Create and submit ticket
        submitTicket(title, description, category, priority, roomNumber);
    }

    private void submitTicket(String title, String description, String category,
                              Priority priority, String roomNumber) {
        // Show loading state
        var userId = firebaseAuth.getCurrentUser().getUid();
        binding.btnSubmitTicket.setEnabled(false);
        binding.btnSubmitTicket.setText("Submitting...");
        Ticket newTicket = new Ticket(title, description, category, priority, userId, roomNumber);
        firebaseInteraction.insertTicket(newTicket, e -> {
            // Hide loading state
            resetSubmitButton();

            if (e != null) {
                showError("Failed to create ticket: " + e.getMessage());
                return;
            }

            showSuccessAndNavigateBack();
        });
    }

    private void resetSubmitButton() {
        binding.btnSubmitTicket.setEnabled(true);
        binding.btnSubmitTicket.setText("Submit Ticket");
    }

    private void showSuccessAndNavigateBack() {
        Toast.makeText(requireContext(), "Ticket created successfully", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigateUp();
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}