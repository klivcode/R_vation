package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ScheduleRequestDTO {
    @NotNull
    private Long busId;
    @NotNull
    private Long routeId;
    @NotNull
    private LocalDate travelTime;

    @NotNull
    private LocalDateTime departureTime;
    @NotNull
    private LocalDateTime arrivalTime;
    @NotNull
    private BigDecimal fareAmount;

}
