package com.busreservationsystem.bus_reservation_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

    private Long bookingId;
    private String customerName;
    private String seatNumber;
    private String ticketNumber;
}