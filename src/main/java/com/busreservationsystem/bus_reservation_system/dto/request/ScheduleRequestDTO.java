package com.busreservationsystem.bus_reservation_system.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ScheduleRequestDTO {

    private LocalDate travelDate;
    private LocalTime departureTime;
    private Double fare;
    private Long busId;
    private Long routeId;
}
