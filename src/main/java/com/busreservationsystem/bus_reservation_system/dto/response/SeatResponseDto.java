package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import com.busreservationsystem.bus_reservation_system.enums.SeatStatus;
import com.busreservationsystem.bus_reservation_system.enums.SeatType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Used when fetching seat layout.
public class SeatResponseDto extends BaseDto {
    private String seatNumber;   // A1, B1, VIP1
    private SeatSide side;
    private Integer rowNumber;
    private SeatType seatType;
    private SeatStatus status;

}
