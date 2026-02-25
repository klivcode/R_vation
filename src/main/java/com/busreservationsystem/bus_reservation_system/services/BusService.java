package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusService {
    BusResponseDTO registerBus(BusRequestDTO busRequestDto);

    List<BusResponseDTO> getAllBus();

    BusResponseDTO getBusById(long id);

    BusResponseDTO updateBusById(BusRequestDTO busRequestDto, long id);

    void deleteBusById(Long id);
}
