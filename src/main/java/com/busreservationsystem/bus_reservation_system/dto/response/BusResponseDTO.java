package com.busreservationsystem.bus_reservation_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusResponseDTO {

    private Long id;
    private String busNumber;
    private Integer totalSeats;
    private Long companyId;
}