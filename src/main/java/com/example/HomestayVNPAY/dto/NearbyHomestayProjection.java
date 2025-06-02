package com.example.HomestayVNPAY.dto;

public interface NearbyHomestayProjection {
    Integer getId();
    String getName();
    String getDescription();
    Double getPricePerNight();
    Double getTotalPrice();
    Double getDistanceMeters();
}
