package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.Booking;
import com.busreservationsystem.bus_reservation_system.entity.Seat;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.projection.SeatVerificationProjection;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookingRepo extends JpaRepository<Booking, Long>,
        JpaSpecificationExecutor<Booking> {

    @Query("""
       SELECT bs.seat.id
       FROM Booking b
       JOIN b.bookingSeats bs
       WHERE b.schedule.id = :scheduleId
       AND b.bookingStatus = :status
       """)
    Set<Long> findBookedSeatIdsByScheduleId(
            @Param("scheduleId") Long scheduleId,
            @Param("status") BookingStatus status
    );

    boolean existsByBookingSeats_Seat_IdAndBookingStatus(
            Long seatId,
            BookingStatus bookingStatus
    );

        List<Booking>
        findAllByBookingStatusAndSchedule_TravelDateBefore(
                BookingStatus status,
                LocalDate date
        );



        // for the report generate

    @Query(value = """
        SELECT 
            s.seat_number AS seatNumber,
            CONCAT(cs.first_name, ' ', cs.last_name) AS customerName,
            b.paid_amount AS paidAmount,
            (b.total_amount - b.paid_amount) AS dueAmount
        FROM bookings b
        JOIN booking_seats bs ON b.id = bs.booking_id
        JOIN seats s ON bs.seat_id = s.id
        JOIN customers cs ON b.customer_id = cs.id
        WHERE b.schedule_id = :scheduleId
          AND b.booking_status = 'BOOKED'
        ORDER BY s.row_number, s.seat_number
    """, nativeQuery = true)
    List<SeatVerificationProjection> findSeatVerificationData(Long scheduleId);


}
