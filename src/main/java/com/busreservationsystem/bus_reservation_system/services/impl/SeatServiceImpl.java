package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.response.SeatLayoutResponseDto;
import com.busreservationsystem.bus_reservation_system.dto.response.SeatResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.Schedule;
import com.busreservationsystem.bus_reservation_system.entity.Seat;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.enums.SeatStatus;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.BookingRepo;
import com.busreservationsystem.bus_reservation_system.repository.ScheduleRepo;
import com.busreservationsystem.bus_reservation_system.repository.SeatRepo;
import com.busreservationsystem.bus_reservation_system.services.SeatService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepo  seatRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private BookingRepo bookingRepo;


    @Override
    public SeatLayoutResponseDto getSeatLayout(long scheduleId) {

        // Check schedule exists
        Schedule schedule = scheduleRepo.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

        // Fetch seats ordered properly
        List<Seat> seats =
                seatRepo.findByScheduleIdOrderByRowNumberAscSeatNumberAsc(scheduleId);

       Set<Long> bookedSeatIds =
               bookingRepo.findBookedSeatIdsByScheduleId(scheduleId,BookingStatus.BOOKED);
 List<SeatResponseDto> seatResponseDtos = new ArrayList<>();

        for (Seat seat : seats) {

            SeatResponseDto dto = new SeatResponseDto();

            dto.setId(seat.getId());
            dto.setSeatNumber(seat.getSeatNumber());
            dto.setSide(seat.getSide());
            dto.setRowNumber(seat.getRowNumber());
            dto.setSeatType(seat.getSeatType());

            //  Derived status
            SeatStatus status = bookedSeatIds.contains(seat.getId())
                    ? SeatStatus.BOOKED
                    : SeatStatus.AVAILABLE;

            dto.setStatus(status);

            seatResponseDtos.add(dto);
        }

        SeatLayoutResponseDto response = new SeatLayoutResponseDto();
        response.setScheduleId(scheduleId);
        response.setTotalSeats(seats.size());
        response.setLayoutPattern(schedule.getBus().getLayoutPattern());
        response.setSeats(seatResponseDtos);

        return response;
    }



    @Override
    public SeatResponseDto getSeatById(Long seatId) {

        Seat seat = seatRepo.findById(seatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seat not found"));

        // derive booking status
        boolean booked = bookingRepo
                .existsBySeats_IdAndBookingStatus(seatId, BookingStatus.BOOKED);

        SeatStatus status = booked
                ? SeatStatus.BOOKED
                : SeatStatus.AVAILABLE;

        SeatResponseDto dto = new SeatResponseDto();
        dto.setId(seat.getId());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setRowNumber(seat.getRowNumber());
        dto.setSide(seat.getSide());
        dto.setSeatType(seat.getSeatType());
        dto.setStatus(status);

        return dto;
    }
}
