package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteRequestDTO {
    @NotBlank
    private String source;
    @NotBlank
    private String destination;
    @NotNull
    private Long companyId;
}