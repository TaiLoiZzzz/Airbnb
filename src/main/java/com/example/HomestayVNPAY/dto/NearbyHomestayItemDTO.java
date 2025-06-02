package com.example.HomestayVNPAY.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NearbyHomestayItemDTO {
    private Integer id;
    private String name;
    private String description;
    private Double pricePerNight;
    private Double totalPrice;
    private Double distanceMeters;
}
