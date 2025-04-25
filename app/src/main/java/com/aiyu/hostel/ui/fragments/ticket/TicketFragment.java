package com.aiyu.hostel.ui.fragments.ticket;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.firebase.FirebaseInteraction;
import com.aiyu.hostel.databinding.FragmentTicketBinding;
import com.aiyu.hostel.utils.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TicketFragment extends BaseFragment {
    public TicketFragment() {
        super(R.layout.fragment_ticket);
    }

    private FragmentTicketBinding binding;

    @Inject
    FirebaseInteraction firebaseInteraction;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentTicketBinding.bind(view);
        var adapter = new TicketAdapter();
        binding.rvTicket.setAdapter(adapter);
        binding.rvTicket.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTicket.setHasFixedSize(true);
        binding.fabAddTicket.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot())
                    .navigate(TicketFragmentDirections.actionTicketFragmentToAddTicketFragment());
        });
        adapter.setClickListener(ticket -> {
            Navigation.findNavController(binding.getRoot())
                    .navigate(
                            TicketFragmentDirections.actionTicketFragmentToTicketDetailFragment(ticket)
                    );
        });
        firebaseInteraction.getAllTicket((tickets, e) -> {
            if (e != null) {
                return;
            }
            if (tickets != null) {
                adapter.submitList(tickets);
            }
        });
    }
}
