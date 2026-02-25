package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.CompanyRepo;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


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
                savedCompany.getCreatedAt(),
                savedCompany.getUpdatedAt(),
                savedCompany.getCompanyName(),
                savedCompany.getAddress(),
                savedCompany.getPhone()
        );
    }

    @Override
    public Page<CompanyResponseDTO> getAllCompany(Pageable pageable) {

        Page<Company> companies = companyRepo.findAll(pageable);

        return companies.map(company -> new CompanyResponseDTO(
                company.getId(),
                company.getCreatedAt(),
                company.getUpdatedAt(),
                company.getCompanyName(),
                company.getAddress(),
                company.getPhone()
        ));
    }
    @Override
    public CompanyResponseDTO getCompanyById(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Company with id " + id + " not found"));
        return new CompanyResponseDTO(
                company.getId(),
                company.getCreatedAt(),
                company.getUpdatedAt(),
                company.getCompanyName(),
                company.getAddress(),
                company.getPhone()

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
                savedCompany.getUpdatedAt(),
                savedCompany.getCreatedAt(),
                savedCompany.getCompanyName(),
                savedCompany.getAddress(),
                savedCompany.getPhone()
        );
    }

    @Override
    public Page<CompanyResponseDTO> getCompanies(String companyName, String address,Pageable pageable) {

        Specification<Company> specification = Specification.where((root,query,cb)->cb.conjunction());
        // first we check the is there presence of CompanyName and to lowercase
        if(companyName != null && !companyName.isEmpty()) {
            specification=specification.and((root,query,cb)->cb
                    .like(
                            cb.lower(root.get("companyName")),
                            "%"+companyName.toLowerCase()+"%")

            );
        }
        // same for the address as well
        if(address != null && !address.isEmpty()) {
            specification =specification.and((root, query, criteriaBuilder) ->  criteriaBuilder
                    .like(
                            criteriaBuilder.lower(root.get("address")),
                            "%"+address.toLowerCase()+"%")

            );

        }

        Page <Company> companies = companyRepo.findAll(specification, pageable);
        return companies
                .map(company -> new CompanyResponseDTO(
                        company.getId(),
                        company.getCreatedAt(),
                        company.getUpdatedAt(),
                        company.getCompanyName(),
                        company.getAddress(),
                        company.getPhone()
                ));
    }


}
