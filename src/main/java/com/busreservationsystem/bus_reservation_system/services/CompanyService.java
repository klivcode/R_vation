package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    CompanyResponseDTO registerCompany(CompanyRequestDTO companyRequestDTO);

    List<CompanyResponseDTO> getAllCompany();

    CompanyResponseDTO getCompanyById(Long id);

    void deleteCompanyById(Long id);

    CompanyResponseDTO updateCompanyById(CompanyRequestDTO requestDto, Long id);
}
