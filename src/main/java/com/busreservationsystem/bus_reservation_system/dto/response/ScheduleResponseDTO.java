package com.busreservationsystem.bus_reservation_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDTO {

    private Long id;
    private LocalDate travelDate;
    private LocalTime departureTime;
    private Double fare;
    private Long busId;
    private Long routeId;
}