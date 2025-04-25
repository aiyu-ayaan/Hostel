package com.aiyu.hostel.ui.fragments.ticket.details;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.Status;
import com.aiyu.hostel.databinding.FragmentTicketDetailsBinding;
import com.aiyu.hostel.utils.BaseFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TicketDetailFragment extends BaseFragment {
    public TicketDetailFragment() {
        super(R.layout.fragment_ticket_details);
    }

    private FragmentTicketDetailsBinding binding;
    private TicketDetailFragmentArgs args;

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentTicketDetailsBinding.bind(view);
        args = TicketDetailFragmentArgs.fromBundle(getArguments());
        var ticket = args.getTicket();

        binding.tvTicketId.setText("TK#" + ticket.getId().substring(0,4));
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
        binding.btnCloseTicket.setOnClickListener(v -> {
//            TODO: Implement close ticket functionality
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
                        AlertDialog alertDialog = (AlertDialog) dialog;
                        int selectedPosition = alertDialog.getListView().getCheckedItemPosition();
                        if (selectedPosition >= 0) {

                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}
