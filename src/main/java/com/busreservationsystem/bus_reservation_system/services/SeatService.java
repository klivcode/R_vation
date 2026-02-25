package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.response.SeatLayoutResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    SeatLayoutResponseDto getSeatLayout(long scheduleId);
}
