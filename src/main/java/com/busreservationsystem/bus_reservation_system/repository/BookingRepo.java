package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.Booking;
import com.busreservationsystem.bus_reservation_system.entity.Seat;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    @Query("""
       SELECT bs.seat.id
       FROM bookings b
       JOIN b.bookingSeats bs
       WHERE b.schedule.id = :scheduleId
       AND b.bookingStatus = :status
       """)
    Set<Long> findBookedSeatIdsByScheduleId(
            @Param("scheduleId") Long scheduleId,
            @Param("status") BookingStatus status
    );

    boolean existsBySeats_IdAndBookingStatus(
            Long seatId,
            BookingStatus bookingStatus
    );

        List<Booking>
        findAllByBookingStatusAndSchedule_TravelDateBefore(
                BookingStatus status,
                LocalDate date
        );


}
