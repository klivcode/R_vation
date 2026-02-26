package com.busreservationsystem.bus_reservation_system.dto.response;

import com.busreservationsystem.bus_reservation_system.dto.BaseDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResponseDto extends BaseDto {

    private String companyName;
    private String address;
    private String phone;


    public CompanyResponseDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String companyName, String address, String phone) {
        super(id, createdAt, updatedAt);
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
    }
}