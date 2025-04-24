package com.aiyu.hostel.ui.fragments.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyu.hostel.core.data.Hostel;
import com.aiyu.hostel.databinding.ItemRoomOptionBinding;

public class RoomOptionAdapter extends ListAdapter<Hostel.RoomOption, RoomOptionAdapter.RoomOptionViewHolder> {

    public RoomOptionAdapter() {
        super(new DiffUtil.ItemCallback<Hostel.RoomOption>() {
            @Override
            public boolean areItemsTheSame(@NonNull Hostel.RoomOption oldItem, @NonNull Hostel.RoomOption newItem) {
                return oldItem.getType().equals(newItem.getType());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Hostel.RoomOption oldItem, @NonNull Hostel.RoomOption newItem) {
                return oldItem.getType().equals(newItem.getType()) &&
                        oldItem.getPrice().equals(newItem.getPrice()) &&
                        oldItem.isAvailable() == newItem.isAvailable();
            }
        });
    }

    interface ClickListener {
        void onRoomOptionClick(Hostel.RoomOption roomOption);
    }

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RoomOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomOptionViewHolder(
                ItemRoomOptionBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RoomOptionViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class RoomOptionViewHolder extends RecyclerView.ViewHolder {
        private ItemRoomOptionBinding binding;

        public RoomOptionViewHolder(
                ItemRoomOptionBinding binding
        ) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Hostel.RoomOption roomOption) {
            binding.tvRoomType.setText(roomOption.getType());
            binding.tvRoomPrice.setText(roomOption.getPrice());
            binding.tvRoomFeatures.setText(roomOption.getDescription());
            binding.tvAvailabilityStatus.setVisibility(
                    roomOption.isAvailable() ? View.VISIBLE : View.GONE
            );
            binding.btnSelect.setEnabled(
                    roomOption.isAvailable()
            );

            binding.btnSelect.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onRoomOptionClick(roomOption);
                }
            });
        }
    }
}
