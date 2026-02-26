package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.ScheduleRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.ScheduleResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDTO);

}
