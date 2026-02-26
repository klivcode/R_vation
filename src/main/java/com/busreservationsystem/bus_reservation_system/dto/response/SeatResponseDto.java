package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.entity.Booking;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import com.busreservationsystem.bus_reservation_system.enums.SeatStatus;
import com.busreservationsystem.bus_reservation_system.enums.SeatType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
// Used when fetching seat layout.
public class SeatResponseDto extends BaseDto {
    private String seatNumber;   // A1, B1, VIP1
    private SeatSide side;
    private Integer rowNumber;
    private SeatType seatType;
    private SeatStatus status;

    public SeatResponseDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String seatNumber, SeatSide side, Integer rowNumber, SeatType seatType, SeatStatus status) {
        super(id, createdAt, updatedAt);
        this.seatNumber = seatNumber;
        this.side = side;
        this.rowNumber = rowNumber;
        this.seatType = seatType;
        this.status = status;
    }
}
