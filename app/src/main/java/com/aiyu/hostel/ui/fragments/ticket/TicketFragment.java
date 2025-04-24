package com.aiyu.hostel.ui.fragments.ticket;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aiyu.hostel.R;
import com.aiyu.hostel.databinding.FragmentTicketBinding;
import com.aiyu.hostel.utils.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TicketFragment extends BaseFragment {
    public TicketFragment() {
        super(R.layout.fragment_ticket);
    }

    private FragmentTicketBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentTicketBinding.bind(view);
    }
}
