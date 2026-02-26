package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BusService {
    BusResponseDto registerBus(BusRequestDto busRequestDto);

    Page<BusResponseDto> getAllBus(Pageable pageable);

    BusResponseDto getBusById(long id);

    BusResponseDto updateBusById(BusRequestDto busRequestDto, long id);

    void deleteBusById(Long id);

    Page<BusResponseDto> searchBus(String busNumber, Boolean hasVipSeat, String busType, Long companyId, Pageable pageable);


}
