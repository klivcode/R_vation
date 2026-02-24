package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface BusService {
    BusResponseDTO registerBus(BusRequestDTO busRequestDto);
}
