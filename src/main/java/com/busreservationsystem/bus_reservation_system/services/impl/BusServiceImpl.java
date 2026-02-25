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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Page<BusResponseDTO> getAllBus(Pageable pageable) {
        Page <Bus> Bus  = busRepo.findAll(pageable);
        return Bus
                .map(bus->new BusResponseDTO(
                        bus.getId(),
                        bus.getCreatedAt(),
                        bus.getUpdatedAt(),
                        bus.getBusNumber(),
                        bus.getTotalSeats(),
                        bus.getBusType(),
                        bus.getLayoutPattern(),
                        bus.getHasVipSeat(),
                        bus.getLastRowExtraSide(),
                        bus.getVipSeatSide(),
                        bus.getCompany().getId(),
                        bus.getCompany().getCompanyName()

                ));
    }

    @Override
    public BusResponseDTO getBusById(long id) {
        Bus bus= busRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return new BusResponseDTO(
                bus.getId(),
                bus.getCreatedAt(),
                bus.getUpdatedAt(),
                bus.getBusNumber(),
                bus.getTotalSeats(),
                bus.getBusType(),
                bus.getLayoutPattern(),
                bus.getHasVipSeat(),
                bus.getLastRowExtraSide(),
                bus.getVipSeatSide(),
                bus.getCompany().getId(),
                bus.getCompany().getCompanyName()

        );
    }

    @Override
    public BusResponseDTO updateBusById(BusRequestDTO busRequestDto, long id) {
        Bus bus = busRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Company not found"));
        bus.setBusNumber(busRequestDto.getBusNumber());
        bus.setBusType(busRequestDto.getBusType());
        bus.setHasVipSeat(busRequestDto.getHasVipSeat());
        bus.setVipSeatSide(busRequestDto.getVipSide());
        bus.setTotalSeats(busRequestDto.getTotalSeats());
        bus.setLayoutPattern(busRequestDto.getLayoutPattern());
        bus.setLastRowExtraSide(busRequestDto.getLastRowExtraSide());
        bus.setCompany(bus.getCompany());
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

    @Override
    public void deleteBusById(Long id) {
        Bus bus = busRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        busRepo.delete(bus);
    }

    @Override
    public Page<BusResponseDTO> getCompany(String busNumber, Boolean hasVipSeat, String busType, Long companyId, Pageable pageable) {
        Specification<Bus> spec = (root, query, cb) -> cb.conjunction();

        if (busNumber != null && !busNumber.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(
                            cb.lower(root.get("busNumber")),
                            busNumber.toLowerCase() + "%"
                    )
            );
        }

        if (hasVipSeat != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("hasVipSeat"), hasVipSeat)
            );
        }

        if (busType != null && !busType.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(
                            cb.lower(root.get("busType")),
                            busType.toLowerCase()
                    )
            );
        }

        if (companyId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("company").get("id"), companyId)
            );
        }

        Page<Bus> buses = busRepo.findAll(spec, pageable);

        return buses
                .map(bus->new BusResponseDTO(
                        bus.getId(),
                        bus.getCreatedAt(),
                        bus.getUpdatedAt(),
                        bus.getBusNumber(),
                        bus.getTotalSeats(),
                        bus.getBusType(),
                        bus.getLayoutPattern(),
                        bus.getHasVipSeat(),
                        bus.getLastRowExtraSide(),
                        bus.getVipSeatSide(),
                        bus.getCompany().getId(),
                        bus.getCompany().getCompanyName()
                ));
    }


}
