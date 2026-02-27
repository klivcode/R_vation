package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.TicketStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketResponseDto extends BaseDto {

    private String ticketNumber;

    private LocalDateTime issueDate;
    private LocalDateTime printedAt;


    private TicketStatus status;

    private String bookingReference;

    public TicketResponseDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String ticketNumber, LocalDateTime issueDate, LocalDateTime printedAt, TicketStatus status, String bookingReference) {
        super(id, createdAt, updatedAt);
        this.ticketNumber = ticketNumber;
        this.issueDate = issueDate;
        this.printedAt = printedAt;
        this.status = status;
        this.bookingReference = bookingReference;
    }
}