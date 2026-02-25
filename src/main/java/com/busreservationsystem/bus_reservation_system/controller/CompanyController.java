package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CompanyController {

    //Base URL: /api/companies
    //
    //Handles transport company management.



    @Autowired
    private CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<CompanyResponseDTO> registerCompany(@RequestBody CompanyRequestDTO companyRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.registerCompany(companyRequestDTO));
    }

    @GetMapping("/companies/getall")
      public ResponseEntity<Page<CompanyResponseDTO>> getAllCompany(Pageable pageable)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getAllCompany(pageable));
    }


    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResponseDTO> getCompany(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getCompanyById(id));
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@RequestBody CompanyRequestDTO requestDto,
                                                            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.updateCompanyById(requestDto,id));

    }


    // Searching (filter) the companyName and address of company

    @GetMapping("/companies")
    public Page<CompanyResponseDTO> searchCompanies(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String address,
            @ParameterObject Pageable pageable
    ) {
        return companyService.searchCompanies(companyName, address, pageable);
    }
}
