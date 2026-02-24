package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import com.busreservationsystem.bus_reservation_system.enums.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto extends BaseDto {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;

}
