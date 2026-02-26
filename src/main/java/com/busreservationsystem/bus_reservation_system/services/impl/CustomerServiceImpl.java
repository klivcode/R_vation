package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.CustomerRequestDto;
import com.busreservationsystem.bus_reservation_system.entity.Customer;
import com.busreservationsystem.bus_reservation_system.repository.CustomerRepo;
import com.busreservationsystem.bus_reservation_system.services.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

//    private final CustomerRepo customerRepository;
//
//    public CustomerServiceImpl(CustomerRepo customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @Transactional
//    public Customer findOrCreateCustomer(CustomerRequestDto dto) {
//
//        String countryCode = normalizeCountryCode(dto.getPhoneNumber());
//        String phone = normalizePhone(dto.getPhoneNumber());
//
//        return customerRepository
//                .findByCountryCodeAndPhoneNumber(countryCode, phone)
//                .orElseGet(() -> {
//                    try {
//                        Customer newCustomer = Customer.builder()
//                                .firstName(dto.getFirstName().trim())
//                                .lastName(dto.getLastName() != null
//                                        ? dto.getLastName().trim()
//                                        : null)
//                                .countryCode(countryCode)
//                                .phoneNumber(phone)
//                                .gender(dto.getGender())
//                                .build();
//
//                        return customerRepository.save(newCustomer);
//
//                    } catch (DataIntegrityViolationException ex) {
//                        // Handle race condition
//                        return customerRepository
//                                .findByCountryCodeAndPhoneNumber(countryCode, phone)
//                                .orElseThrow();
//                    }
//                });
//    }
//
//    private String normalizePhone(String phone) {
//        return phone.trim().replaceAll("\\s+", "");
//    }
//    private String normalizeCountryCode(String countryCode) {
//
//        if (countryCode == null || countryCode.isBlank()) {
//            throw new IllegalArgumentException("Country code cannot be null or empty");
//        }
//
//        // Remove all spaces
//        String cleaned = countryCode.trim().replaceAll("\\s+", "");
//
//        // Remove leading +
//        if (cleaned.startsWith("+")) {
//            cleaned = cleaned.substring(1);
//        }
//
//        // Ensure only digits
//        if (!cleaned.matches("\\d{1,4}")) {
//            throw new IllegalArgumentException("Invalid country code format");
//        }
//
//        return "+" + cleaned;
//    }
}
