package com.aiyu.hostel.core.data;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

    // Constants for ticket priority

    // Constants for ticket status




    private String id;
    private String title;
    private String description;
    private String category;
    private Priority priority;
    private Status status;
    private Date createdAt;
    private Date updatedAt;
    private String userId;
    private String roomNumber;
    private String assignedToStaffId;

    // Default constructor
    public Ticket() {
        // Initialize with current date
        this.createdAt = new Date();
        this.updatedAt = new Date();
        // Set default status to pending
        this.status = Status.PENDING;
    }

    // Constructor with essential fields
    public Ticket(String title, String description, String category, Priority priority, String userId, String roomNumber) {
        this();
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.userId = userId;
        this.roomNumber = roomNumber;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(int Priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        // Update time whenever status changes
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAssignedToStaffId() {
        return assignedToStaffId;
    }

    public void setAssignedToStaffId(String assignedToStaffId) {
        this.assignedToStaffId = assignedToStaffId;
    }




    // Get color resource ID for status
    public int getStatusColorResource() {
        switch (status) {
            case PENDING:
                return android.R.color.holo_orange_light; // Use your defined color: R.color.status_pending
            case IN_PROGRESS:
                return android.R.color.holo_blue_light;   // Use your defined color: R.color.status_inprogress
            case RESOLVED:
            case CLOSED:
                return android.R.color.holo_green_light;  // Use your defined color: R.color.status_resolved
            default:
                return android.R.color.darker_gray;
        }
    }
}