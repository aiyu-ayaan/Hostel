package com.aiyu.hostel.ui.fragments.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyu.hostel.R;
import com.aiyu.hostel.core.data.Amenity;
import com.aiyu.hostel.core.data.Hostel;
import com.aiyu.hostel.databinding.ItemHostelBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class HomeAdapter extends ListAdapter<Hostel, HomeAdapter.HostelViewHolder> {

    private OnHostelClickListener listener;

    public interface OnHostelClickListener {
        void onHostelClick(Hostel hostel);

        void onViewDetailsClick(Hostel hostel);

        void onFavoriteClick(Hostel hostel, boolean isFavorite);
    }

    private static final DiffUtil.ItemCallback<Hostel> DIFF_CALLBACK = new DiffUtil.ItemCallback<Hostel>() {
        @Override
        public boolean areItemsTheSame(@NonNull Hostel oldItem, @NonNull Hostel newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Hostel oldItem, @NonNull Hostel newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getLocation().equals(newItem.getLocation()) &&
                    oldItem.getRating() == newItem.getRating() &&
                    oldItem.getPricePerMonth().equals(newItem.getPricePerMonth()) &&
                    oldItem.getAvailableBeds() == newItem.getAvailableBeds() &&
                    oldItem.getAmenities().equals(newItem.getAmenities());
        }
    };

    public HomeAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setOnHostelClickListener(OnHostelClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HostelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHostelBinding binding = ItemHostelBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new HostelViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HostelViewHolder holder, int position) {
        Hostel hostel = getItem(position);
        holder.bind(hostel, listener);
    }

    static class HostelViewHolder extends RecyclerView.ViewHolder {
        private final ItemHostelBinding binding;

        public HostelViewHolder(ItemHostelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Hostel hostel, final OnHostelClickListener listener) {
            // Set basic info
            binding.tvHostelName.setText(hostel.getName());
            binding.tvHostelLocation.setText(hostel.getLocation());
            binding.rbHostelRating.setRating(hostel.getRating());
            binding.tvHostelPrice.setText(hostel.getPricePerMonth());

            // Set availability status
            if (hostel.getAvailableBeds() > 0) {
                binding.tvAvailability.setText(R.string.available);
            } else {
                binding.tvAvailability.setText(R.string.full);

            }

            // Load hostel image
            if (hostel.getImageUrls() != null && !hostel.getImageUrls().isEmpty()) {
                Glide.with(binding.ivHostelImage.getContext())
                        .load(hostel.getImageUrls().get(0))
                        .error(R.drawable.img_base)
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                        .into(binding.ivHostelImage);
            } else {
                binding.ivHostelImage.setImageResource(R.drawable.baseline_home_24);
            }

            // Setup amenities visibility
            binding.tvWifi.setVisibility(hostel.hasAmenity(Amenity.WIFI) ? View.VISIBLE : View.GONE);
            binding.tvAc.setVisibility(hostel.hasAmenity(Amenity.AC) ? View.VISIBLE : View.GONE);
            binding.tvFood.setVisibility(hostel.hasAmenity(Amenity.FOOD) ? View.VISIBLE : View.GONE);
            binding.tvLaundry.setVisibility(hostel.hasAmenity(Amenity.LAUNDRY) ? View.VISIBLE : View.GONE);

            // Set click listeners
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onHostelClick(hostel);
                }
            });

            binding.btnViewDetails.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onViewDetailsClick(hostel);
                }
            });

        }
    }
}
