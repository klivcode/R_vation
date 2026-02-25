package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.ScheduleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleResponseDTO extends BaseDto {

    private LocalDate travelDate;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal fareAmount;
    private ScheduleStatus scheduleStatus;
    private Long busId;
    private String busNumber;
    private Long routeId;
    private String source;
    private String destination;

    public ScheduleResponseDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDate travelDate, LocalDateTime departureTime, LocalDateTime arrivalTime, BigDecimal fareAmount, ScheduleStatus scheduleStatus, Long busId, String busNumber, Long routeId, String source, String destination) {
        super(id, createdAt, updatedAt);
        this.travelDate = travelDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fareAmount = fareAmount;
        this.scheduleStatus = scheduleStatus;
        this.busId = busId;
        this.busNumber = busNumber;
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
    }
}