package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.BookingRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.BookingResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.*;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.enums.PaymentState;
import com.busreservationsystem.bus_reservation_system.enums.PaymentStatus;
import com.busreservationsystem.bus_reservation_system.repository.*;
import com.busreservationsystem.bus_reservation_system.services.BookingReferenceGeneratorService;
import com.busreservationsystem.bus_reservation_system.services.BookingService;
import com.busreservationsystem.bus_reservation_system.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private BookingSeatRepo bookingSeatRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;



    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {

        // find or Create Customer

        Customer customer = customerRepo
                .findByPhoneNumber(bookingRequestDto.getPhoneNumber())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setFirstName(bookingRequestDto.getFirstName());
                    newCustomer.setLastName(bookingRequestDto.getLastName());
                    newCustomer.setPhoneNumber(bookingRequestDto.getPhoneNumber());
                    return customerRepo.save(newCustomer);
                });


        //validate Schedule

        Schedule schedule = scheduleRepo.findById(bookingRequestDto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        if (schedule.getTravelDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Cannot book past schedule");
        }


        // generate Booking Reference

        BookingReferenceGeneratorServiceImpl bookingReferenceGeneratorService = new BookingReferenceGeneratorServiceImpl();
        String bookingReference = bookingReferenceGeneratorService.generateReference();

        Booking booking = new Booking();
        booking.setBookingReference(bookingReference);
        booking.setCustomer(customer);
        booking.setSchedule(schedule);
        booking.setBookingDateTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.BOOKED);


        // process Seats (LOCK + CHECK)

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<BookingSeat> bookingSeats = new ArrayList<>();

        for (Long seatId : bookingRequestDto.getSeatIds()) {

            // Lock seat row
            Seat seat = seatRepo.findByIdForUpdate(seatId)
                    .orElseThrow(() -> new RuntimeException("Seat not found"));

            // Prevent double booking
            boolean alreadyBooked =
                    bookingSeatRepo.existsByScheduleAndSeat(schedule, seat);

            if (alreadyBooked) {
                throw new RuntimeException(
                        "Seat already booked: " + seat.getSeatNumber());
            }

            BookingSeat bookingSeat = new BookingSeat();
            bookingSeat.setBooking(booking);
            bookingSeat.setSchedule(schedule);
            bookingSeat.setSeat(seat);

            BigDecimal seatPrice = schedule.getFareAmount();
            bookingSeat.setSeatPrice(seatPrice);

            totalAmount = totalAmount.add(seatPrice);
            bookingSeats.add(bookingSeat);
        }

        booking.setBookingSeats(bookingSeats);
        booking.setTotalAmount(totalAmount);


        // Payment Calculation

        BigDecimal paidAmount = bookingRequestDto.getPaidAmount() == null
                ? BigDecimal.ZERO
                : bookingRequestDto.getPaidAmount();

        booking.setPaidAmount(paidAmount);

        BigDecimal dueAmount = totalAmount.subtract(paidAmount);
        booking.setDueAmount(dueAmount);

        if (dueAmount.compareTo(BigDecimal.ZERO) == 0) {
            booking.setPaymentState(PaymentState.PAID);
        } else if (paidAmount.compareTo(BigDecimal.ZERO) > 0) {
            booking.setPaymentState(PaymentState.PARTIAL);
        } else {
            booking.setPaymentState(PaymentState.UNPAID);
        }

        Booking savedBooking=bookingRepo.save(booking);

        String customerName = bookingRequestDto.getFirstName() + " " + bookingRequestDto.getLastName();
        List<String> busNumbers = List.of(
                booking.getSchedule()
                        .getBus()
                        .getBusNumber()
        );
        return new BookingResponseDto(
                    savedBooking.getId(),
                savedBooking.getCreatedAt(),
                savedBooking.getUpdatedAt(),
                savedBooking.getBookingReference(),
                savedBooking.getSchedule().getId(),
                savedBooking.getCustomer().getId(),
                customerName,
                savedBooking.getSchedule().getRoute().toString(),
                savedBooking.getSchedule().getTravelDate(),
                busNumbers,
                savedBooking.getBookingStatus(),
                savedBooking.getTotalAmount(),
                savedBooking.getPaidAmount(),
                savedBooking.getDueAmount(),
                savedBooking.getPaymentState(),
                savedBooking.getBookingDateTime()
        );
    }
}
