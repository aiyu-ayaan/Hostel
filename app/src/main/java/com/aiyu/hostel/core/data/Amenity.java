package com.aiyu.hostel.core.data;

public enum Amenity {
    WIFI("WiFi"),
    AC("AC"),
    FOOD("Food"),
    LAUNDRY("Laundry"),
    GYM("Gym"),
    STUDY_ROOM("Study Room"),
    PARKING("Parking");

    private final String displayName;

    Amenity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}