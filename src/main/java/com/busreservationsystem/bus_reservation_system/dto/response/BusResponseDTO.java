package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusResponseDTO extends BaseDto {

    private String busNumber;
    private Integer totalSeats;
    private String busType;
    private String layoutPattern;
    private Boolean hasVipSeat;
    private SeatSide  seatSide;
    private SeatSide  lastRowExtraSeatSide;
    private Long companyId;
    private String companyName;
}