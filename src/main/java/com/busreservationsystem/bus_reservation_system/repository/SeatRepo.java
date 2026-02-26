package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatRepo extends JpaRepository<Seat, Long> {

    List<Seat> findByScheduleIdOrderByRowNumberAscSeatNumberAsc(Long scheduleId);
    //This prevents two counters booking same seat simultaneously.

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.id = :seatId")
    Optional<Seat> findByIdForUpdate(Long seatId);
}
