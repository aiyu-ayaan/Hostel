package com.aiyu.hostel.core.data;

import android.os.Build;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hostel implements Serializable {
    private String id;
    private String name;
    private String location;
    private float rating;
    private int reviewCount;
    private String pricePerMonth;
    private List<String> imageUrls;
    private List<Amenity> amenities;
    private String description;
    private List<String> policies;
    private List<RoomOption> roomOptions;
    private int availableBeds;
    private String contactInfo;

    // Enum for Amenities


    // Default constructor
    public Hostel() {
        this.imageUrls = new ArrayList<>();
        this.amenities = new ArrayList<>();
        this.policies = new ArrayList<>();
        this.roomOptions = new ArrayList<>();
    }

    // Constructor with all fields
    public Hostel(String id, String name, String location, float rating,
                  int reviewCount, String pricePerMonth, List<String> imageUrls,
                  List<Amenity> amenities, String description, List<String> policies,
                  List<RoomOption> roomOptions, int availableBeds, String contactInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.pricePerMonth = pricePerMonth;
        this.imageUrls = imageUrls != null ? imageUrls : new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            this.amenities = amenities != null ? amenities.stream().toList() : new ArrayList<>();
        } else {
            this.amenities = amenities != null ? new ArrayList<>(amenities) : new ArrayList<>();
        }
        this.description = description;
        this.policies = policies != null ? policies : new ArrayList<>();
        this.roomOptions = roomOptions != null ? roomOptions : new ArrayList<>();
        this.availableBeds = availableBeds;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(String pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls != null ? imageUrls : new ArrayList<>();
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities != null ? amenities : new ArrayList<>();
    }

    // Add a single amenity
    public void addAmenity(Amenity amenity) {
        this.amenities.add(amenity);
    }

    // Check if hostel has a specific amenity
    public boolean hasAmenity(Amenity amenity) {
        return this.amenities.contains(amenity);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPolicies() {
        return policies;
    }

    public void setPolicies(List<String> policies) {
        this.policies = policies != null ? policies : new ArrayList<>();
    }

    public List<RoomOption> getRoomOptions() {
        return roomOptions;
    }

    public void setRoomOptions(List<RoomOption> roomOptions) {
        this.roomOptions = roomOptions != null ? roomOptions : new ArrayList<>();
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Room Option inner class to represent the different room types
    public static class RoomOption implements Serializable {
        private String type;
        private String price;
        private String description;
        private boolean isAvailable;
        private List<String> features;

        public RoomOption() {
            this.features = new ArrayList<>();
        }

        public RoomOption(String type, String price, String description, boolean isAvailable, List<String> features) {
            this.type = type;
            this.price = price;
            this.description = description;
            this.isAvailable = isAvailable;
            this.features = features != null ? features : new ArrayList<>();
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features != null ? features : new ArrayList<>();
        }
    }
}