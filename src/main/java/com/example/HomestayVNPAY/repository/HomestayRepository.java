package com.example.HomestayVNPAY.repository;

import com.example.HomestayVNPAY.dto.NearbyHomestayProjection;
import com.example.HomestayVNPAY.entity.Homestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomestayRepository  extends JpaRepository<Homestay, Integer> {
    @Query(value = """
    SELECT
        h.id,
        h.name,
        h.description,
        ha.price AS price_per_night,
        (ha.price * :numNights) AS total_price
        
    FROM homestay h
    JOIN homestay_availability ha
        ON h.id = ha.homestay_id
    WHERE
        h.guests >= :requiredGuests
        AND ha.date >= :checkinDate
        AND ha.date < :checkoutDate
        AND ha.status = 0
        AND ST_DWithin(
            h.geom,
            ST_SetSRID(ST_MakePoint(:longitude, :latitude), 3857),
            :radius
        )
    GROUP BY h.id, ha.price
    HAVING COUNT(ha.date) = :numNights
    ORDER BY ST_Distance(h.geom, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 3857)) ASC
    """, nativeQuery = true)
    List<NearbyHomestayProjection> findNearbyHomestays(
            @Param("longitude") double longitude,
            @Param("latitude") double latitude,
            @Param("radius") double radius,
            @Param("checkinDate") LocalDate checkinDate,
            @Param("checkoutDate") LocalDate checkoutDate,
            @Param("requiredGuests") int requiredGuests,
            @Param("numNights") int numNights
    );

}
