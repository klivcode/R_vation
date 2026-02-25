package com.busreservationsystem.bus_reservation_system.repository;

import com.busreservationsystem.bus_reservation_system.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyRepo extends JpaRepository<Company, Long>,
        JpaSpecificationExecutor<Company>
        // JpaSpecificationExecutor is used for the dynamic filtering
{

}
