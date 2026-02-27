package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.TicketPrintDto;
import com.busreservationsystem.bus_reservation_system.dto.response.TicketResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.Booking;
import com.busreservationsystem.bus_reservation_system.entity.Ticket;

public interface TicketService {
//    Ticket generateTicket(Booking booking);

    TicketResponseDto getTicket(Long id);

    TicketResponseDto getByBooking(Long bookingId);

//    void markAsPrinted(Long id);

    TicketResponseDto generateTicket(Booking booking);

    TicketPrintDto getTicketForPrint(Long id);
}
