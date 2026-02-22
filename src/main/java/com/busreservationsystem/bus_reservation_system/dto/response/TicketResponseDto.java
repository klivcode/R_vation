package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;

import java.time.LocalDateTime;

public class TicketResponseDto extends BaseDto {

    private String ticketNumber;
    private LocalDateTime issueDate;
    private String qrCodeUrl;

    private String bookingReference;
}
