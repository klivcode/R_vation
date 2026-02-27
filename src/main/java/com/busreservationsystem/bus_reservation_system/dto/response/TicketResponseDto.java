package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.TicketStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto extends BaseDto {

    private String ticketNumber;

    private LocalDateTime issueDate;
    private LocalDateTime printedAt;

    private String qrCodeUrl;

    private TicketStatus status;

    private String bookingReference;
}