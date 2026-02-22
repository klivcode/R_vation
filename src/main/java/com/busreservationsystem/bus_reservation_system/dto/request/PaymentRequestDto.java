package com.busreservationsystem.bus_reservation_system.dto.request;

import com.busreservationsystem.bus_reservation_system.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentRequestDto {

    @NotNull
    private Long bookingId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PaymentMethod paymentMethod;


}
