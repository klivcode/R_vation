package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.ScheduleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDTO extends BaseDto {

    private LocalDate travelDate;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double fareAmount;
    private ScheduleStatus scheduleStatus;
    private Long busId;
    private String busNumber;
    private Long routeId;
    private String source;
    private String destination;
}