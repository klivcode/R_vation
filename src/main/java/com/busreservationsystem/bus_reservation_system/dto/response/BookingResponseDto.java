package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.enums.PaymentState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto extends BaseDto {
    private String bookingReference;

    private Long scheduleId;
    private Long customerId;

    private String customerName;        // Better for UI
    private String route;               // e.g. KTM → Pokhara
    private LocalDate travelDate;
    private Integer passengerCount;
    private List<String> seatNumbers;

    private BookingStatus status;
    private String busNumbers;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;

    private PaymentState paymentState;
    private LocalDateTime bookingDateTime;

    public BookingResponseDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String bookingReference, Long scheduleId, Long customerId, String customerName,Integer passengerCount, String route, LocalDate travelDate,String busNumbers, List<String> seatNumbers, BookingStatus status, BigDecimal totalAmount, BigDecimal paidAmount, BigDecimal dueAmount, PaymentState paymentState, LocalDateTime bookingDateTime) {
        super(id, createdAt, updatedAt);
        this.bookingReference = bookingReference;
        this.scheduleId = scheduleId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.passengerCount = passengerCount;
        this.route = route;
        this.travelDate = travelDate;
        this.busNumbers = busNumbers;
        this.seatNumbers = seatNumbers;
        this.status = status;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.paymentState = paymentState;
        this.bookingDateTime = bookingDateTime;
    }
}
