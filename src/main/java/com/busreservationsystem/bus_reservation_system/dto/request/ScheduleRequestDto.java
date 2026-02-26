package com.busreservationsystem.bus_reservation_system.dto.request;

import com.busreservationsystem.bus_reservation_system.enums.ScheduleStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {
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
    private ScheduleStatus scheduleStatus;
    @NotNull
    private BigDecimal fareAmount;

}
