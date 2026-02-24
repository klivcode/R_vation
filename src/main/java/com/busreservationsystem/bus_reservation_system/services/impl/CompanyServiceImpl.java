package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.CompanyRepo;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

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
                        savedCompany.getPhone(),
                        savedCompany.getCreatedAt(),
                        savedCompany.getUpdatedAt()
        );
    }

    @Override
    public List<CompanyResponseDTO> getAllCompany() {
        List<Company> companies = companyRepo.findAll();
        return companies.stream()
                .map(company -> new CompanyResponseDTO(
                        company.getId(),
                        company.getCompanyName(),
                        company.getAddress(),
                        company.getPhone()
                ))
                .toList();
    }

    @Override
    public CompanyResponseDTO getCompanyById(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Company with id " + id + " not found"));
        return new CompanyResponseDTO(
                company.getId(),
                company.getCompanyName(),
                company.getAddress(),
                company.getPhone(),
                company.getCreatedAt(),
                company.getUpdatedAt()
        );
    }

    @Override
    public void deleteCompanyById(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Company with id " + id + " not found"));
        companyRepo.delete(company);

    }

    @Override
    public CompanyResponseDTO updateCompanyById(CompanyRequestDTO requestDto, Long id) {
        Company company = companyRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Company with id " + id + " not found"));
        company.setCompanyName(requestDto.getCompanyName());
        company.setAddress(requestDto.getAddress());
        company.setPhone(requestDto.getPhone());
        Company savedCompany = companyRepo.save(company);
        return new CompanyResponseDTO(
                savedCompany.getId(),
                savedCompany.getCompanyName(),
                savedCompany.getAddress(),
                savedCompany.getPhone(),
                savedCompany.getUpdatedAt(),
                savedCompany.getCreatedAt()
        );
    }


}
