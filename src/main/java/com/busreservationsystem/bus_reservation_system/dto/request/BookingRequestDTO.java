package com.busreservationsystem.bus_reservation_system.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestDTO {

    private Long customerId;
    private Long seatId;
}