package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    CompanyResponseDTO registerCompany(CompanyRequestDTO companyRequestDTO);

    Page<CompanyResponseDTO> getAllCompany(Pageable pageable);

    CompanyResponseDTO getCompanyById(Long id);

    void deleteCompanyById(Long id);

    CompanyResponseDTO updateCompanyById(CompanyRequestDTO requestDto, Long id);

    Page<CompanyResponseDTO> getCompanies(String companyName, String address, Pageable pageable);
}
