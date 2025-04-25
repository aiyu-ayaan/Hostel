package com.aiyu.hostel.ui.fragments.ticket;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.Ticket;
import com.aiyu.hostel.databinding.ItemTicketBinding;

public class TicketAdapter extends ListAdapter<Ticket, TicketAdapter.TicketViewHolder> {

    protected TicketAdapter() {
        super(new DiffUtil.ItemCallback<Ticket>() {
            @Override
            public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
                return oldItem.getId().equals(newItem.getId()) &&
                        oldItem.getTitle().equals(newItem.getTitle()) &&
                        oldItem.getDescription().equals(newItem.getDescription()) &&
                        oldItem.getCategory().equals(newItem.getCategory());
            }
        });
    }

    interface ClickListener {
        void onTicketClick(Ticket ticket);
    }

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketViewHolder(
                ItemTicketBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {
        private ItemTicketBinding binding;

        public TicketViewHolder(ItemTicketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Ticket ticket) {
            binding.tvTicketId.setText("TK#" + ticket.getId().substring(0,4));
            binding.tvTicketTitle.setText(ticket.getTitle());
            binding.tvTicketDescription.setText(ticket.getDescription());
            binding.tvTicketCategory.setText(ticket.getCategory());
            binding.tvTicketPriority.setText(ticket.getPriority().toString());
            binding.tvTicketStatus.setText(ticket.getStatus().toString());
            binding.getRoot().setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onTicketClick(ticket);
                }
            });
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
            switch (ticket.getPriority()) {
                case LOW:
                    binding.tvTicketPriority.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.status_inprogress));
                    break;
                case MEDIUM:
                    binding.tvTicketPriority.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.status_pending));
                    break;
                case HIGH:
                    binding.tvTicketPriority.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.red));
            }
        }
    }
}
