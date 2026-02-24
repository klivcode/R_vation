package com.busreservationsystem.bus_reservation_system.dto.request;

import com.busreservationsystem.bus_reservation_system.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CustomerRequestDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String phoneNumber;
    private Gender gender;

}
