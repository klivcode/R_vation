package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Bus;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.BusRepo;
import com.busreservationsystem.bus_reservation_system.repository.CompanyRepo;
import com.busreservationsystem.bus_reservation_system.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepo  busRepo;
    @Autowired
    private CompanyRepo companyRepo;


    @Override
    public BusResponseDTO registerBus(BusRequestDTO busRequestDto) {

        Company company = companyRepo.findById(busRequestDto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        Bus bus = new Bus();
        bus.setBusNumber(busRequestDto.getBusNumber());
        bus.setBusType(busRequestDto.getBusType());
        bus.setHasVipSeat(busRequestDto.getHasVipSeat());
        bus.setVipSeatSide(busRequestDto.getVipSide());
        bus.setTotalSeats(busRequestDto.getTotalSeats());
        bus.setLayoutPattern(busRequestDto.getLayoutPattern());
        bus.setLastRowExtraSide(busRequestDto.getLastRowExtraSide());
        bus.setCompany(company);

        Bus savedBus = busRepo.save(bus);

        return new BusResponseDTO(
                savedBus.getId(),
                savedBus.getCreatedAt(),
                savedBus.getUpdatedAt(),
                savedBus.getBusNumber(),
                savedBus.getTotalSeats(),
                savedBus.getBusType(),
                savedBus.getLayoutPattern(),
                savedBus.getHasVipSeat(),
                savedBus.getVipSeatSide(),
                savedBus.getLastRowExtraSide(),
                savedBus.getCompany().getId(),
                savedBus.getCompany().getCompanyName()
        );
    }
}
