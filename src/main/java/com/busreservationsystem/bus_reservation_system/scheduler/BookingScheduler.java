package com.busreservationsystem.bus_reservation_system.scheduler;

import com.busreservationsystem.bus_reservation_system.entity.Booking;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.repository.BookingRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingScheduler {

    private final BookingRepo bookingRepository;

    @Transactional
    @Scheduled(cron = "0 0 1 * * ?")
    // Runs every day at 1 AM
    public void autoCompleteExpiredBookings() {

        LocalDate today = LocalDate.now();

        List<Booking> expiredBookings =
                bookingRepository
                        .findAllByBookingStatusAndSchedule_TravelDateBefore(
                                BookingStatus.BOOKED,
                                today
                        );

        for (Booking booking : expiredBookings) {
            booking.setBookingStatus(BookingStatus.COMPLETED);
        }
    }
}