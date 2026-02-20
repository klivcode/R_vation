package com.busreservationsystem.bus_reservation_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteResponseDTO {

    private Long id;
    private String source;
    private String destination;
    private Long companyId;
}