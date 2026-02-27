package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.BookingSeat;
import com.busreservationsystem.bus_reservation_system.entity.Schedule;
import com.busreservationsystem.bus_reservation_system.entity.Seat;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BookingSeatRepo extends JpaRepository<BookingSeat, Long> {




    boolean existsByScheduleAndSeat(Schedule schedule, Seat seat);
}
