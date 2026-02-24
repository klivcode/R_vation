package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CompanyRequestDTO {

    @NotBlank
    private String companyName;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;
}