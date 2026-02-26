package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RouteResponseDto extends BaseDto {

    private String source;
    private String destination;
    private Long companyId;

    public RouteResponseDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String source, String destination, Long companyId) {
        super(id, createdAt, updatedAt);
        this.source = source;
        this.destination = destination;
        this.companyId = companyId;
    }
}