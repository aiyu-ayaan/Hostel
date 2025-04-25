package com.aiyu.hostel.utils;


import android.util.Log;

import com.aiyu.hostel.core.firebase.FirebaseInteraction;

import javax.inject.Inject;

public class InsertItemToDatabase {
    private static final String TAG = "InsertItemToDatabase";

    @Inject
    FirebaseInteraction firebaseInteraction;

    @Inject
    public InsertItemToDatabase() {
    }

    public void insertData() {
        insertFoodData();
        insertHostelData();
        insertTicketData();
    }
    public void insertHostelData() {
        DataGenerator.getHostels().forEach(hostel -> {
            firebaseInteraction.insertHostel(hostel, e -> {
                if (e != null) {
                    // Handle error
                    Log.e(TAG, "Error inserting hostel: " + e.getMessage());
                } else {
                    // Handle success
                    Log.d(TAG, "Hostel inserted successfully: " + hostel.getName());
                }
            });
        });
    }
    public void insertFoodData() {
        DataGenerator.getFoodItems().forEach(foodItem -> {
            firebaseInteraction.insertFood(foodItem, e -> {
                if (e != null) {
                    // Handle error
                    Log.e(TAG, "Error inserting food item: " + e.getMessage());
                } else {
                    // Handle success
                    Log.d(TAG, "Food item inserted successfully: " + foodItem.getName());
                }
            });
        });
    }
    public void insertTicketData() {
        DataGenerator.getTicketSampleData().forEach(ticket -> {
            firebaseInteraction.insertTicket(ticket, e -> {
                if (e != null) {
                    // Handle error
                    Log.e(TAG, "Error inserting ticket: " + e.getMessage());
                } else {
                    // Handle success
                    Log.d(TAG, "Ticket inserted successfully: " + ticket.getTitle());
                }
            });
        });
    }
}
