package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Override
    public CompanyResponseDTO registerCompany(CompanyRequestDTO companyRequestDTO) {
        // TODO

        return null;
    }

    @Override
    public List<CompanyResponseDTO> getAllCompany() {
        //TODO
        return List.of();
    }
}
