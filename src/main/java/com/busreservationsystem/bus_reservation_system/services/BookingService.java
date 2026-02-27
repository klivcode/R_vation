package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.BookingRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.BookingResponseDto;
import com.busreservationsystem.bus_reservation_system.dto.response.TicketResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    BookingResponseDto createBooking(@Valid BookingRequestDto bookingRequestDto);

    TicketResponseDto getGeneratedTicket(long bookingId);
}
