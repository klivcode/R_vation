package com.busreservationsystem.bus_reservation_system.controller;


import com.busreservationsystem.bus_reservation_system.entity.Ticket;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TicketController {


    // Get ticket by booking
    @GetMapping("/bookings/{bookingId}/ticket")
    public  getTickets(@PathVariable("bookingId") String bookingId) {}
}
