package com.aiyu.hostel.ui.fragments.ticket.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.Status;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentTicketDetailsBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TicketDetailFragment extends BaseFragment {
    public TicketDetailFragment() {
        super(R.layout.fragment_ticket_details);
    }

    private FragmentTicketDetailsBinding binding;
    private TicketDetailFragmentArgs args;

    @Inject
    FirebaseInteraction firebaseInteraction;


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentTicketDetailsBinding.bind(view);
        args = TicketDetailFragmentArgs.fromBundle(getArguments());
        var ticket = args.getTicket();

        binding.tvTicketId.setText("TK#" + ticket.getId().substring(0, 4));
        binding.tvTicketTitle.setText(ticket.getTitle());
        binding.tvTicketDescription.setText(ticket.getDescription());
        binding.tvTicketCategory.setText(ticket.getCategory());
        binding.tvTicketPriority.setText(ticket.getPriority().toString());
        binding.tvRoomNumber.setText(ticket.getRoomNumber());
        switch (ticket.getPriority()) {
            case LOW:
                binding.tvTicketPriority.setTextColor(ContextCompat.getColor(requireContext(), R.color.status_inprogress));
                break;
            case MEDIUM:
                binding.tvTicketPriority.setTextColor(ContextCompat.getColor(requireContext(), R.color.status_pending));
                break;
            case HIGH:
                binding.tvTicketPriority.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
        }
        binding.tvTicketStatus.setText(ticket.getStatus().toString());
        switch (ticket.getStatus()) {
            case PENDING:
                binding.tvTicketStatus.setBackgroundResource(R.drawable.status_pending_bg);
                break;
            case IN_PROGRESS:
                binding.tvTicketStatus.setBackgroundResource(R.drawable.status_in_progress_bg);
                break;
            case CLOSED:
            case RESOLVED:
                binding.tvTicketStatus.setBackgroundResource(R.drawable.status_in_resolve_bg);
                break;
        }
        binding.btnCloseTicket.setText(
                ticket.getStatus() == Status.CLOSED ? "Reopen Ticket" : "Close Ticket"
        );
        binding.btnCloseTicket.setOnClickListener(v -> {
            ticket.setStatus(
                    ticket.getStatus() == Status.CLOSED ? Status.PENDING : Status.CLOSED
            );
            firebaseInteraction.updateTicket(
                    ticket, e -> {
                        if (e != null) {
                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(requireContext(), "Ticket Closed", Toast.LENGTH_SHORT).show();
                        navigateUp();
                    }
            );
        });
        binding.btnUpdateStatus.setOnClickListener(v -> {

            final Status[] statuses = Status.values();
            final String[] statusLabels = new String[statuses.length];
            int checkedItem = ticket.getStatus().ordinal();
            // Create a list of status labels
            for (int i = 0; i < statuses.length; i++) {
                statusLabels[i] = statuses[i].name().replace("_", " "); // Make readable
            }
            new MaterialAlertDialogBuilder(requireContext())
                    .setSingleChoiceItems(statusLabels, checkedItem, null)
                    .setPositiveButton("Update", (dialog, which) -> {
                        androidx.appcompat.app.AlertDialog alertDialog = (androidx.appcompat.app.AlertDialog) dialog;
                        int selectedPosition = alertDialog.getListView().getCheckedItemPosition();
                        if (selectedPosition >= 0) {
                            Status selectedStatus = statuses[selectedPosition];
                            ticket.setStatus(selectedStatus);
                            firebaseInteraction.updateTicket(
                                    ticket, e1 -> {
                                        if (e1 != null) {
                                            Toast.makeText(requireContext(), e1.getMessage(), Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        Toast.makeText(requireContext(), "Ticket Status Updated", Toast.LENGTH_SHORT).show();
                                        navigateUp();
                                    }
                            );
                        } else {
                            Toast.makeText(requireContext(), "No status selected", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    private void navigateUp() {
        Navigation.findNavController(binding.getRoot())
                .navigateUp();
    }
}
