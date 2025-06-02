package com.example.HomestayVNPAY.API;

import com.example.HomestayVNPAY.dto.NearbyHomestayItemDTO;
import com.example.HomestayVNPAY.dto.NearbyHomestayProjection;
import com.example.HomestayVNPAY.dto.NearbyHomestayResponse;
import com.example.HomestayVNPAY.entity.Homestay;
import com.example.HomestayVNPAY.service.HomestayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/v1/homestays")
@RequiredArgsConstructor
public class HomestayController {
    @Autowired
     HomestayService homestayService;

    @GetMapping
    public List<Homestay> getAllHomestays() {
        return homestayService.getAllHomestay();
    }
    @GetMapping("/search")
    public NearbyHomestayResponse getNearbyHomestays(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam double radius,
            @RequestParam("checkin_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkinDate,
            @RequestParam("checkout_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkoutDate,
            @RequestParam int guests
    ) {
        int nights = (int) ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        List<NearbyHomestayItemDTO> results = homestayService.results(
                longitude, latitude, radius, checkinDate, checkoutDate, guests, nights
        );

        return new NearbyHomestayResponse(
                new NearbyHomestayResponse.Meta("SUCCESS", "Quach Tai Hay"),
                results
        );
    }


}
