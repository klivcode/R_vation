package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    CompanyResponseDto registerCompany(CompanyRequestDto companyRequestDTO);

    Page<CompanyResponseDto> getAllCompany(Pageable pageable);

    CompanyResponseDto getCompanyById(Long id);

    void deleteCompanyById(Long id);

    CompanyResponseDto updateCompanyById(CompanyRequestDto requestDto, Long id);

    Page<CompanyResponseDto> searchCompanies(String companyName, String address, Pageable pageable);
}
