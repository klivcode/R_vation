package com.busreservationsystem.bus_reservation_system.dto.request;

import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRequestDTO {

    @NotBlank
    private String busNumber;
    @NotBlank
    private String busType;
    @NotNull
    private Integer totalSeats;
    @NotBlank
    private String layoutPattern;
    private Boolean hasVipSeat;
    private SeatSide vipSide;
    private SeatSide lastRowExtraSide;
    @NotNull
    private Long companyId;
}