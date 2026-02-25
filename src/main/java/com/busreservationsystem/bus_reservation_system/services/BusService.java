package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusService {
    BusResponseDTO registerBus(BusRequestDTO busRequestDto);

    Page<BusResponseDTO> getAllBus(Pageable pageable);

    BusResponseDTO getBusById(long id);

    BusResponseDTO updateBusById(BusRequestDTO busRequestDto, long id);

    void deleteBusById(Long id);

    Page<BusResponseDTO> searchBus(String busNumber, Boolean hasVipSeat, String busType, Long companyId, Pageable pageable);


}
