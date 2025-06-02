package com.example.HomestayVNPAY.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NearbyHomestayResponse {
    private Meta meta;
    private List<NearbyHomestayItemDTO> data;

    @Data
    @AllArgsConstructor
    public static class Meta {
        private String status;
        private String service_id ;
    }
}
