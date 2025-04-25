package com.aiyu.hostel.core.data;

import androidx.annotation.Keep;

@Keep
public class BuyFood {

    private String userId;
    private FoodItem foodItem;
    private String hostelName;
    private String RoomNumber;
    private Long createdAt;

    public BuyFood() {
    }

    public BuyFood(String userId, FoodItem foodItem, String hostelName, String roomNumber, Long createdAt) {
        this.userId = userId;
        this.foodItem = foodItem;
        this.hostelName = hostelName;
        this.RoomNumber = roomNumber;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
