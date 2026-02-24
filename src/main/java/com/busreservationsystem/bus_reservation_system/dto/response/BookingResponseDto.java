package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.enums.PaymentState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto extends BaseDto {
    private String bookingReference;
    private Long scheduleId;
    private Long customerId;

    private List<String> seatNumbers;

    private BookingStatus status;

    private BigDecimal Amount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;

    private PaymentState paymentState;

}
