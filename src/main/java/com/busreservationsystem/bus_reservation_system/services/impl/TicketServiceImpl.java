package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.TicketPrintDto;
import com.busreservationsystem.bus_reservation_system.dto.response.TicketResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.Booking;
import com.busreservationsystem.bus_reservation_system.entity.Customer;
import com.busreservationsystem.bus_reservation_system.entity.Ticket;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.enums.TicketStatus;
import com.busreservationsystem.bus_reservation_system.exception.TicketNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.BookingRepo;
import com.busreservationsystem.bus_reservation_system.repository.TicketRepo;
import com.busreservationsystem.bus_reservation_system.services.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepo  ticketRepo;
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private BookingReferenceGeneratorService  bookingReferenceGeneratorService;


    @Override
    public TicketResponseDto generateTicket(Booking booking) {
        if (ticketRepo.existsByBooking_Id(booking.getId())) {
            throw new IllegalStateException("Ticket already generated");
        }

        if (booking.getBookingStatus() != BookingStatus.BOOKED) {
            throw new IllegalStateException("Booking not confirmed");
        }

        Ticket ticket = new Ticket();
        ticket.setBooking(booking);
        ticket.setIssueDate(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ISSUED);
        ticket.setTicketNumber(generateTicketNumber(booking));
//        ticket.setQrCodeUrl(generateQrCode(booking));

         Ticket savedTicket=ticketRepo.save(ticket);
         return new TicketResponseDto(
                 savedTicket.getId(),
                 savedTicket.getCreatedAt(),
                 savedTicket.getUpdatedAt(),
                 savedTicket.getTicketNumber(),
                 savedTicket.getIssueDate(),
                 savedTicket.getPrintedAt(),
                 savedTicket.getStatus(),
                 savedTicket.getBooking().getBookingReference()
         );
    }



    @Override
    public TicketResponseDto getTicket(Long id) {

        Ticket ticket = ticketRepo.findById(id)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found with id: " + id)
                );

        return new TicketResponseDto(
                ticket.getId(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getTicketNumber(),
                ticket.getIssueDate(),
                ticket.getPrintedAt(),
                ticket.getStatus(),
                ticket.getBooking().getBookingReference()
        );
    }


    @Override
    public TicketResponseDto getByBooking(Long bookingId) {

        Ticket ticket = ticketRepo.findByBooking_Id(bookingId)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found for booking id: " + bookingId)
                );

        return new TicketResponseDto(
                ticket.getId(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getTicketNumber(),
                ticket.getIssueDate(),
                ticket.getPrintedAt(),
                ticket.getStatus(),
                ticket.getBooking().getBookingReference()
        );
    }

//    @Override
//    @Transactional
//    public void markAsPrinted(Long ticketId) {
//
//        Ticket ticket = ticketRepo.findById(ticketId)
//                .orElseThrow(() ->
//                        new TicketNotFoundException("Ticket not found with id: " + ticketId)
//                );
//
//        if (ticket.getStatus() == TicketStatus.PRINTED) {
//            throw new IllegalStateException("Ticket already printed");
//        }
//
//        if (ticket.getStatus() == TicketStatus.CANCELLED) {
//            throw new IllegalStateException("Cannot print cancelled ticket");
//        }
//
//        ticket.setStatus(TicketStatus.PRINTED);
//        ticket.setPrintedAt(LocalDateTime.now());
//    }


    @Override
    public TicketPrintDto getTicketForPrint(Long id) {

        Ticket ticket = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Booking booking = ticket.getBooking();

        BigDecimal dueAmount = booking.getTotalAmount()
                .subtract(booking.getPaidAmount());
        String customerName = booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName();
        List<String> seatNumbers = booking.getBookingSeats()
                .stream()
                .map(bs -> bs.getSeat().getSeatNumber())
                .toList();
        return new TicketPrintDto(
                ticket.getTicketNumber(),
                ticket.getIssueDate(),
                ticket.getPrintedAt(),
                ticket.getStatus(),
                booking.getBookingReference(),
                customerName,
                booking.getSchedule().getRoute().getSource()+" TO "+booking.getSchedule().getRoute().getDestination(),
                booking.getSchedule().getTravelDate(),
                seatNumbers,
                booking.getPassengerCount(),
                booking.getTotalAmount(),
                booking.getPaidAmount(),
                dueAmount,
                booking.getSchedule().getBus().getBusNumber()
        );
    }




    private String generateTicketNumber(Booking booking) {
        return "TKT-" + booking.getBookingReference();
    }

//    private String generateQrCode(Booking booking) {
//        return "https://yourdomain.com/verify/" + booking.getBookingReference();
//    }
}
