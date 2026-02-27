package com.busreservationsystem.bus_reservation_system.dto;

import com.busreservationsystem.bus_reservation_system.entity.BookingSeat;
import com.busreservationsystem.bus_reservation_system.enums.TicketStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TicketPrintDto {

    private String ticketNumber;
    private LocalDateTime issueDate;
    private LocalDateTime printedAt;
    private TicketStatus status;

    private String bookingReference;
    private String customerName;
    private String route;
    private LocalDate travelDate;
    private List<String> seatNumbers;
    private Integer countPassengers;

    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;

    private String busNumber;

    public TicketPrintDto(String ticketNumber, LocalDateTime issueDate, LocalDateTime printedAt, TicketStatus status, String bookingReference, String customerName, String route, LocalDate travelDate, List<String> seatNumbers, Integer countPassengers, BigDecimal totalAmount, BigDecimal paidAmount, BigDecimal dueAmount, String busNumber) {
        this.ticketNumber = ticketNumber;
        this.issueDate = issueDate;
        this.printedAt = printedAt;
        this.status = status;
        this.bookingReference = bookingReference;
        this.customerName = customerName;
        this.route = route;
        this.travelDate = travelDate;
        this.seatNumbers = seatNumbers;
        this.countPassengers = countPassengers;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.busNumber = busNumber;
    }
}