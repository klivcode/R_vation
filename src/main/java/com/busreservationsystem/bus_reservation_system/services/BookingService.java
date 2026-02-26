package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.BookingRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.BookingResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    BookingResponseDto createBooking(@Valid BookingRequestDto bookingRequestDto);
}
