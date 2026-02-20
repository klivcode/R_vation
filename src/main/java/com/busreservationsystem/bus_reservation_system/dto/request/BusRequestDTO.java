package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRequestDTO {

    @NotBlank
    private String busNumber;

    @NotNull
    private Integer totalSeats;

    @NotNull
    private Long companyId;
}