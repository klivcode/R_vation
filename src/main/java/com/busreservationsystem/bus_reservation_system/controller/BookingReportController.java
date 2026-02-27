package com.busreservationsystem.bus_reservation_system.controller;


import com.busreservationsystem.bus_reservation_system.services.BookingReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class BookingReportController {
    @Autowired
    private BookingReportService bookingReportService;

    @GetMapping("/seat-verification")
    public ResponseEntity<InputStreamResource> downloadReport(
            @RequestParam Long scheduleId) throws IOException {

        ByteArrayInputStream file =
                bookingReportService.exportSeatVerificationExcel(scheduleId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=SeatVerification.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(file));
    }
}
