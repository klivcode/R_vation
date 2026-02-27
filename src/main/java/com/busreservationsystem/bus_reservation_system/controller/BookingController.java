package com.busreservationsystem.bus_reservation_system.controller;


import com.busreservationsystem.bus_reservation_system.dto.request.BookingRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.BookingResponseDto;
import com.busreservationsystem.bus_reservation_system.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingService.createBooking(bookingRequestDto));
    }

    @GetMapping("/booking")
    public ResponseEntity<Page<BookingResponseDto>> getBooking(Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookingService.getBooked(pageable));
    }
}
