package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.ScheduleRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.ScheduleResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.*;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.*;
import com.busreservationsystem.bus_reservation_system.services.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private BusRepo busRepo;
    @Autowired
    private RouteRepo routeRepo;
    @Autowired
    private BusSeatRepo busSeatRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Override
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDTO) {
      // validate the id for the bus and route firstly
        Bus bus = busRepo.findById(scheduleRequestDTO.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        Route route = routeRepo.findById(scheduleRequestDTO.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        Schedule schedule = new Schedule();
        schedule.setBus(bus);
        schedule.setRoute(route);
        schedule.setTravelDate(scheduleRequestDTO.getTravelTime());
        schedule.setArrivalTime(scheduleRequestDTO.getArrivalTime());
        schedule.setDepartureTime(scheduleRequestDTO.getDepartureTime());
        schedule.setFareAmount(scheduleRequestDTO.getFareAmount());
        schedule.setScheduleStatus(scheduleRequestDTO.getScheduleStatus());
        Schedule savedSchedule = scheduleRepo.save(schedule);
        generateScheduleSeats(savedSchedule);
        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt(),
               savedSchedule.getTravelDate(),
                savedSchedule.getDepartureTime(),
                savedSchedule.getArrivalTime(),
                savedSchedule.getFareAmount(),
                savedSchedule.getScheduleStatus(),
                savedSchedule.getBus().getId(),
                savedSchedule.getBus().getBusNumber(),
                savedSchedule.getRoute().getId(),
                savedSchedule.getRoute().getSource(),
                savedSchedule.getRoute().getDestination()

        );
    }

    private void generateScheduleSeats(Schedule schedule) {

        List<BusSeat> templates =
                busSeatRepo.findByBusId(schedule.getBus().getId());

        List<Seat> seats = new ArrayList<>();

        for (BusSeat template : templates) {

            Seat seat = new Seat();
            seat.setSchedule(schedule);
            seat.setSeatNumber(template.getSeatNumber());
            seat.setRowNumber(template.getRowNumber());
            seat.setSide(template.getSide());
            seat.setSeatType(template.getSeatType());

            seats.add(seat);
        }

        seatRepo.saveAll(seats);
    }
}
