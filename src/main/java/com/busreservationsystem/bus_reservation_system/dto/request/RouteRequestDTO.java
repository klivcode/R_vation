package com.busreservationsystem.bus_reservation_system.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteRequestDTO {

    private String source;
    private String destination;
    private Long companyId;
}