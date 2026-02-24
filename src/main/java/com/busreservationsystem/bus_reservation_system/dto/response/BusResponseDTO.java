package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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

    public BusResponseDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String busNumber, Integer totalSeats, String busType, String layoutPattern, Boolean hasVipSeat, SeatSide seatSide, SeatSide lastRowExtraSeatSide, Long companyId, String companyName) {
        super(id, createdAt, updatedAt);
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.busType = busType;
        this.layoutPattern = layoutPattern;
        this.hasVipSeat = hasVipSeat;
        this.seatSide = seatSide;
        this.lastRowExtraSeatSide = lastRowExtraSeatSide;
        this.companyId = companyId;
        this.companyName = companyName;
    }

}