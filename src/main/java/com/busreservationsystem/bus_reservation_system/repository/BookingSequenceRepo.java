package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.BookingSequence;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingSequenceRepo
        extends JpaRepository<BookingSequence, LocalDate> {



     @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
           SELECT bs
           FROM BookingSequence bs
           WHERE bs.bookingDate = :date
           """)
    Optional<BookingSequence> findByDateForUpdate(
            @Param("date") LocalDate date
    );}
//
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query("SELECT bs FROM BookingSequence bs WHERE bs.bookingDate = :date")
//    Optional<BookingSequence> findByDateForUpdate(LocalDate date);
//}
