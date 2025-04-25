package com.aiyu.hostel.core.data;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private int preparationTimeMinutes;
    private float rating;
    private int ratingCount;
    private boolean isFavorite;
    private String category;
    private boolean isAvailable;

    // Enum for dietary types

    // Default constructor
    public FoodItem() {
    }

    // Full constructor
    public FoodItem(String id, String name, String description, double price,
                    String imageUrl, int preparationTimeMinutes, float rating,
                    int ratingCount, boolean isFavorite,
                    String category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.preparationTimeMinutes = preparationTimeMinutes;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.isFavorite = isFavorite;
        this.category = category;
        this.isAvailable = isAvailable;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(int preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Helper method to get formatted price (e.g., "$12.99")
    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }

    // Helper method to get formatted preparation time (e.g., "15 min")
    public String getFormattedPreparationTime() {
        return preparationTimeMinutes + " min";
    }
}
