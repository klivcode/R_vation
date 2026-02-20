package com.busreservationsystem.bus_reservation_system.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDTO {

    private Long customerId;
    private Long seatId;
}