package com.example.HomestayVNPAY.service;

import com.example.HomestayVNPAY.dto.NearbyHomestayItemDTO;
import com.example.HomestayVNPAY.dto.NearbyHomestayProjection;
import com.example.HomestayVNPAY.entity.Homestay;
import com.example.HomestayVNPAY.repository.HomestayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomestayService {

    private final HomestayRepository homestayRepository;

    public List<Homestay> getAllHomestay() {
        return homestayRepository.findAll();
    }
    public List<NearbyHomestayItemDTO> results(
            double longitude, double latitude, double radius,
            LocalDate checkinDate, LocalDate checkoutDate,
            int guests, int numNights) {

        List<NearbyHomestayProjection> projections = homestayRepository.findNearbyHomestays(
                longitude, latitude, radius, checkinDate, checkoutDate, guests, numNights
        );

        return projections.stream().map(p -> new NearbyHomestayItemDTO(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPricePerNight(),
                p.getTotalPrice(),
                p.getDistanceMeters()
        )).toList();
    }


}
