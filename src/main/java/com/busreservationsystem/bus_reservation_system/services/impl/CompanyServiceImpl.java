package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.repository.CompanyRepo;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
   private CompanyRepo companyRepo;

    @Override
    public CompanyResponseDTO registerCompany(CompanyRequestDTO companyRequestDTO) {

        Company company = new Company();
        company.setCompanyName(companyRequestDTO.getCompanyName());
        company.setAddress(companyRequestDTO.getAddress());
        company.setPhone(companyRequestDTO.getPhone());
        Company savedCompany = companyRepo.save(company);
        return new CompanyResponseDTO(
                         savedCompany.getId(),
                        savedCompany.getCompanyName(),
                        savedCompany.getAddress(),
                        savedCompany.getPhone());
    }

    @Override
    public List<CompanyResponseDTO> getAllCompany() {
        List<Company> companies = companyRepo.findAll();
        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();
        return companies.stream()
                .map(company -> new CompanyResponseDTO(
                        company.getId(),
                        company.getCompanyName(),
                        company.getAddress(),
                        company.getPhone()
                ))
                .toList();
    }
}
