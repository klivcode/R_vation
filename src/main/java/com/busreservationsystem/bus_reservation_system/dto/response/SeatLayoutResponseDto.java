package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SeatLayoutResponseDto extends BaseDto {
    private Long scheduleId;
    private Integer totalSeats;
    private List<SeatResponseDto> seats;
}
