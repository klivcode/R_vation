package com.busreservationsystem.bus_reservation_system.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface BookingReportService {

    ByteArrayInputStream exportSeatVerificationExcel(Long scheduleId) throws IOException;
}