package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class RouteRequestDto {
    @NotBlank
    private String source;
    @NotBlank
    private String destination;
    @NotNull
    private Long companyId;
}