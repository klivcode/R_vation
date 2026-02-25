package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.BusSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusSeatRepo extends JpaRepository<BusSeat, Long> {

    List<BusSeat> findByBusId(Long busId);
}
