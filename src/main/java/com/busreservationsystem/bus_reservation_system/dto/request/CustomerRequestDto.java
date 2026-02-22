package com.busreservationsystem.bus_reservation_system.dto.request;

import com.busreservationsystem.bus_reservation_system.enums.Gender;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequestDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String phoneNumber;
    private Gender gender;

}
