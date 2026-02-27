package com.busreservationsystem.bus_reservation_system.projection;

import java.math.BigDecimal;

public interface SeatVerificationProjection {

    String getSeatNumber();
    String getCustomerName();
    BigDecimal getPaidAmount();
    BigDecimal getDueAmount();
}