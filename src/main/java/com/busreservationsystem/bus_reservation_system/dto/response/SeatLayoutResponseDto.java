package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

    public class SeatLayoutResponseDto {
        private Long scheduleId;
        private Integer totalSeats;
        private String layoutPattern;
        private List<SeatResponseDto> seats;

    public SeatLayoutResponseDto(Long scheduleId, Integer totalSeats, String layoutPattern, List<SeatResponseDto> seats) {
        this.scheduleId = scheduleId;
        this.totalSeats = totalSeats;
        this.layoutPattern = layoutPattern;
        this.seats = seats;
    }
}
