package com.busreservationsystem.bus_reservation_system.controller;


import com.busreservationsystem.bus_reservation_system.dto.TicketPrintDto;
import com.busreservationsystem.bus_reservation_system.dto.response.TicketResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.Ticket;
import com.busreservationsystem.bus_reservation_system.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;


    // ticket by ticket id
    @GetMapping("/{id}")
    public TicketResponseDto getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    // Get printable data
    @GetMapping("/{id}/print")
    public ResponseEntity<TicketPrintDto> getPrintData(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketService.getTicketForPrint(id));
    }

    // Get ticket by booking id
    @GetMapping("/by-booking/{bookingId}")
    public TicketResponseDto getByBooking(@PathVariable Long bookingId) {
        return ticketService.getByBooking(bookingId);
    }

//    @PutMapping("/{id}/print")
//    public void markAsPrinted(@PathVariable Long id) {
//        ticketService.markAsPrinted(id);
//    }
}