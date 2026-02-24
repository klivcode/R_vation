package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.entity.Booking;
import com.busreservationsystem.bus_reservation_system.enums.PaymentMethod;
import com.busreservationsystem.bus_reservation_system.enums.PaymentState;
import com.busreservationsystem.bus_reservation_system.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto extends BaseDto {
    private String paymentReference;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private LocalDateTime paymentTime;

    private String bookingReference;

    private BigDecimal bookingTotalAmount;

    private BigDecimal bookingPaidAmount;

    private BigDecimal bookingDueAmount;

    private PaymentState bookingPaymentState;
}
