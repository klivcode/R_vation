package com.busreservationsystem.bus_reservation_system.dto;

import java.time.LocalDateTime;

public abstract class BaseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
