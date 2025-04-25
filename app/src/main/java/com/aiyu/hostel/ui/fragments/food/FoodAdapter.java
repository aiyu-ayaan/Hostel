package com.aiyu.hostel.ui.fragments.food;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.FoodItem;
import com.aiyu.hostel.databinding.ItemFoodBinding;
import com.bumptech.glide.Glide;

public class FoodAdapter extends ListAdapter<FoodItem, FoodAdapter.FoodItemHolder> {

    protected FoodAdapter() {
        super(new DiffUtil.ItemCallback<FoodItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull FoodItem oldItem, @NonNull FoodItem newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull FoodItem oldItem, @NonNull FoodItem newItem) {
                return oldItem.getName().equals(newItem.getName()) ||
                        oldItem.getDescription().equals(newItem.getDescription()) ||
                        oldItem.getPrice() == newItem.getPrice() ||
                        oldItem.getPreparationTimeMinutes() == newItem.getPreparationTimeMinutes() ||
                        oldItem.getRating() == newItem.getRating() ||
                        oldItem.getRatingCount() == newItem.getRatingCount() ||
                        oldItem.isFavorite() == newItem.isFavorite() ||
                        oldItem.getCategory().equals(newItem.getCategory()) ||
                        oldItem.isAvailable() == newItem.isAvailable();
            }
        });
    }

    public interface OnFoodItemClickListener {
        void onFoodItemClick(FoodItem foodItem);

        void onBuyFoodClick(FoodItem foodItem);
    }

    private OnFoodItemClickListener listener;

    public void setOnFoodItemClickListener(OnFoodItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodItemHolder(
                ItemFoodBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemHolder holder, int position) {
        FoodItem foodItem = getItem(position);
        holder.bind(foodItem);
    }

    public class FoodItemHolder extends RecyclerView.ViewHolder {

        private ItemFoodBinding binding;

        public FoodItemHolder(ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FoodItem foodItem) {
            binding.tvFoodName.setText(foodItem.getName());
            binding.tvFoodDescription.setText(foodItem.getDescription());
            binding.tvFoodPrice.setText(String.valueOf(foodItem.getPrice()));
            binding.tvPreparationTime.setText(String.valueOf(foodItem.getPreparationTimeMinutes()));
            binding.rbFoodRating.setRating(foodItem.getRating());
            Glide.with(binding.getRoot())
                    .load(foodItem.getImageUrl())
                    .error(R.drawable.ic_food_base)
                    .into(binding.ivFoodImage);
            if (listener != null) {
                binding.getRoot().setOnClickListener(v -> listener.onFoodItemClick(foodItem));
            }
            binding.btnAddToBuy.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBuyFoodClick(foodItem);
                }
            });
        }
    }
}
