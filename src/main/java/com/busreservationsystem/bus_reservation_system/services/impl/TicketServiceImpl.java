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
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;


import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
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
        ticket.setPrintedAt(LocalDateTime.now());
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
                booking.getSchedule().getDepartureTime(),
                booking.getSchedule().getBus().getBusNumber()
        );
    }



    @Override
    public byte[] generateThermalPdf(TicketPrintDto dto) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            Rectangle pageSize = new Rectangle(226f, 700f);
            Document document = new Document(pageSize, 10, 10, 10, 10);

            PdfWriter.getInstance(document, out);
            document.open();

            Font font = new Font(Font.COURIER, 8);

            DateTimeFormatter dtf =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            DateTimeFormatter df =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            document.add(new Paragraph("================================", font));
            document.add(new Paragraph("           BUS TICKET", font));
            document.add(new Paragraph("================================", font));

            document.add(new Paragraph("Ticket No : " + dto.getTicketNumber(), font));
            document.add(new Paragraph("Status    : " + dto.getStatus(), font));
            document.add(new Paragraph("Issued    : " + dto.getIssueDate().format(dtf), font));
            document.add(new Paragraph("Printed   : " + dto.getPrintedAt().format(dtf), font));

            document.add(new Paragraph("--------------------------------", font));

            document.add(new Paragraph("Booking   : " + dto.getBookingReference(), font));
            document.add(new Paragraph("Customer  : " + dto.getCustomerName(), font));
            document.add(new Paragraph("Route     : " + dto.getRoute(), font));
            document.add(new Paragraph("Travel    : " + dto.getTravelDate().format(df), font));
            document.add(new Paragraph("Departure : " + dto.getDepartureDateTime().format(dtf), font));
            document.add(new Paragraph("Bus No    : " + dto.getBusNumber(), font));

            document.add(new Paragraph("Seats     : " +
                    String.join(", ", dto.getSeatNumbers()), font));

            document.add(new Paragraph("Passengers: " + dto.getCountPassengers(), font));

            document.add(new Paragraph("--------------------------------", font));

            document.add(new Paragraph("Total     : " + dto.getTotalAmount(), font));
            document.add(new Paragraph("Paid      : " + dto.getPaidAmount(), font));
            document.add(new Paragraph("Due       : " + dto.getDueAmount(), font));

            document.add(new Paragraph("================================", font));
            document.add(new Paragraph("           Thank You!", font));
            document.add(new Paragraph("================================", font));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Thermal PDF generation failed", e);
        }

        return out.toByteArray();
    }




    private String generateTicketNumber(Booking booking) {
        return "TKT-" + booking.getBookingReference();
    }

//    private String generateQrCode(Booking booking) {
//        return "https://yourdomain.com/verify/" + booking.getBookingReference();
//    }
}
