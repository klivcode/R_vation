package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
