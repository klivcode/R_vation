package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookingRequestDto {
    @NotNull
    private Long scheduleId;

    @NotNull
    private Long customerId;

    @NotEmpty
    private List<Long> seatIds;   // Multiple seats

}
