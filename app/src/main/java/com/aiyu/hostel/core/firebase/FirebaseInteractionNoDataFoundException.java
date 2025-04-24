package com.aiyu.hostel.core.firebase;

public class FirebaseInteractionNoDataFoundException extends Exception {
    public FirebaseInteractionNoDataFoundException() {
        super("No data found");
    }
}
