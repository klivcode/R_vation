package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RouteRepo extends JpaRepository<Route, Long> ,
        JpaSpecificationExecutor<Route> {
}
