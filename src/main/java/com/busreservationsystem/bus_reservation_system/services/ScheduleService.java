package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.ScheduleRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.ScheduleResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Schedule;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    ScheduleResponseDTO createSchedule(ScheduleRequestDTO scheduleRequestDTO);

}
