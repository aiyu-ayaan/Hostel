package com.aiyu.hostel.ui.fragments.food.history;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.BuyFood;
import com.aiyu.hostel.databinding.OrderFoodItemBinding;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrdersAdapter extends ListAdapter<BuyFood, OrdersAdapter.OrderViewHolder> {

    protected OrdersAdapter() {
        super(new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull BuyFood oldItem, @NonNull BuyFood newItem) {
                return oldItem.getCreatedAt().equals(newItem.getCreatedAt());
            }

            @Override
            public boolean areContentsTheSame(@NonNull BuyFood oldItem, @NonNull BuyFood newItem) {
                return oldItem.getCreatedAt().equals(newItem.getCreatedAt()) &&
                        oldItem.getFoodItem().getName().equals(newItem.getFoodItem().getName()) &&
                        oldItem.getHostelName().equals(newItem.getHostelName()) &&
                        oldItem.getRoomNumber().equals(newItem.getRoomNumber());
            }
        });
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(
                OrderFoodItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        BuyFood order = getItem(position);
        holder.bind(order);
    }


    // Helper method to set status background color


    // ViewHolder class
    static class OrderViewHolder extends RecyclerView.ViewHolder {
        private OrderFoodItemBinding binding;

        OrderViewHolder(
                @NonNull OrderFoodItemBinding binding
        ) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BuyFood order) {
            binding.tvFoodName.setText(order.getFoodItem().getName());
            binding.tvPrice.setText(order.getFoodItem().getFormattedPrice());
            binding.tvDeliveryLocation.setText("Delivery to: " + order.getHostelName() + ", Room " + order.getRoomNumber());
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault());
            String formattedDate = sdf.format(new Date(order.getCreatedAt()));
            binding.tvOrderedTime.setText(formattedDate);
            Glide.with(binding.getRoot())
                    .load(order.getFoodItem().getImageUrl())
                    .centerCrop()
                    .error(R.drawable.ic_food_base)
                    .into(binding.ivFoodImage);
            setStatusBackgroundColor(binding.tvStatus, getOrderStatus(order));

        }

        private void setStatusBackgroundColor(TextView statusView, String status) {
            int color;

            switch (status.toLowerCase()) {
                case "preparing":
                    color = Color.parseColor("#FFA500"); // Orange
                    break;
                case "on the way":
                    color = Color.parseColor("#2196F3"); // Blue
                    break;
                case "delivered":
                    color = Color.parseColor("#4CAF50"); // Green
                    break;
                case "cancelled":
                    color = Color.parseColor("#F44336"); // Red
                    break;
                default:
                    color = Color.parseColor("#9E9E9E"); // Grey
            }

            // Apply the color to the background drawable
            DrawableCompat.setTint(statusView.getBackground(), color);
            statusView.setText(status);
        }

        // Helper method to get order status (you would replace this with your actual logic)
        private String getOrderStatus(BuyFood order) {
            // This is a placeholder. In a real app, you would determine the status based on your business logic
            long currentTime = System.currentTimeMillis();
            long orderTime = order.getCreatedAt();
            long timeDifference = currentTime - orderTime;

            // Example logic - you should replace with your own status determination
            if (timeDifference < 10 * 60 * 1000) { // Less than 10 minutes
                return "Preparing";
            } else if (timeDifference < 20 * 60 * 1000) { // Less than 20 minutes
                return "On the way";
            } else {
                return "Delivered";
            }
        }
    }
}
