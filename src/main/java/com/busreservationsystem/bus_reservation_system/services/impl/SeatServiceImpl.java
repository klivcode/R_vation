package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.response.SeatLayoutResponseDto;
import com.busreservationsystem.bus_reservation_system.dto.response.SeatResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.Schedule;
import com.busreservationsystem.bus_reservation_system.entity.Seat;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.ScheduleRepo;
import com.busreservationsystem.bus_reservation_system.repository.SeatRepo;
import com.busreservationsystem.bus_reservation_system.services.SeatService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepo  seatRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;
    @Override
    public SeatLayoutResponseDto getSeatLayout(long scheduleId) {
        // checks
        Schedule schedule = scheduleRepo.findById(scheduleId).
                orElseThrow(()->new ResourceNotFoundException("Schedule not found"));
        // fetch the seats
//        List<Seat> seats = seatRepo.findByScheduleId(scheduleId);

        // 2️fetch seats ordered properly
        List<Seat> seats =
                seatRepo.findByScheduleIdOrderByRowNumberAscSeatNumberAsc(scheduleId);

        List<SeatResponseDto> seatResponseDtos = new ArrayList<>();
        // Entity -> DTO
        for (Seat seat : seats) {

            SeatResponseDto dto = new SeatResponseDto();

            dto.setId(seat.getId());
            dto.setSeatNumber(seat.getSeatNumber());
            dto.setSide(seat.getSide());
            dto.setRowNumber(seat.getRowNumber());
            dto.setSeatType(seat.getSeatType());
            dto.setStatus(seat.getSeatStatus());
            seatResponseDtos.add(dto);
        }
        // Response for the layout of the Seat
        SeatLayoutResponseDto seatLayoutResponseDto = new SeatLayoutResponseDto();
        seatLayoutResponseDto.setScheduleId(scheduleId);
        seatLayoutResponseDto.setTotalSeats(seats.size());

        // Example: layout pattern coming from Bus
        seatLayoutResponseDto.setLayoutPattern(schedule.getBus().getLayoutPattern());

        seatLayoutResponseDto.setSeats(seatResponseDtos);
        return seatLayoutResponseDto;

    }
}
