package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
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
      public ResponseEntity<List<CompanyResponseDTO>> getAllCompany()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getAllCompany());
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
}
