package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDto;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CompanyResponseDto> registerCompany(@RequestBody CompanyRequestDto companyRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.registerCompany(companyRequestDTO));
    }

    @GetMapping("/companies/getall")
      public ResponseEntity<Page<CompanyResponseDto>> getAllCompany(Pageable pageable)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getAllCompany(pageable));
    }


    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResponseDto> getCompany(@PathVariable Long id) {
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
    public ResponseEntity<CompanyResponseDto> updateCompany(@RequestBody CompanyRequestDto requestDto,
                                                            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.updateCompanyById(requestDto,id));

    }


    // Searching (filter) the companyName and address of company
    @Tag(name = "Company API", description = "APIs for managing and searching transport companies")
    @Operation(
            summary = "Search Companies",
            description = "Search companies using optional filters such as company name and address with pagination and sorting support."
    )
    @GetMapping("/companies")
    public Page<CompanyResponseDto> searchCompanies(


            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String address,
            @ParameterObject Pageable pageable
    ) {
        return companyService.searchCompanies(companyName, address, pageable);
    }
}
