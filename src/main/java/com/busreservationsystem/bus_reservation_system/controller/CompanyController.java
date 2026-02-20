package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.CompanyRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.CompanyResponseDTO;
import com.busreservationsystem.bus_reservation_system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/operator/company/register")
    public ResponseEntity<CompanyResponseDTO> registerCompany(@RequestBody CompanyRequestDTO companyRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.registerCompany(companyRequestDTO));
    }

    @GetMapping("/operator/company/getall")
      public ResponseEntity<List<CompanyResponseDTO>> getAllCompany()
    {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(companyService.getAllCompany());
    }


}
