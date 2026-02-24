package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestDto {
    @NotNull
    private Long scheduleId;

    @NotNull
    private Long customerId;

    @NotEmpty
    private List<Long> seatIds;   // Multiple seats

}
