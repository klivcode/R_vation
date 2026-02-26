package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.BookingSeat;
import com.busreservationsystem.bus_reservation_system.entity.Schedule;
import com.busreservationsystem.bus_reservation_system.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSeatRepo extends JpaRepository<BookingSeat, Long> {

    boolean existsByScheduleAndSeat(Schedule schedule, Seat seat);
}
