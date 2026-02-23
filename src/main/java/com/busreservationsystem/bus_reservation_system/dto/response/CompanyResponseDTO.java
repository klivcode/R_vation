package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class CompanyResponseDTO extends BaseDto {

    private String companyName;
    private String address;
    private String phone;

    public CompanyResponseDTO(Long id, String companyName, String address, String phone) {
        super();
    }

    public CompanyResponseDTO(Long id, String companyName, String address, String phone, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
    }
}