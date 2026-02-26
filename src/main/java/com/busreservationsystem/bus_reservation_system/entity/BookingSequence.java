package com.busreservationsystem.bus_reservation_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "booking_sequence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingSequence {

    @Id
    private LocalDate bookingDate;   // 2026-02-26

    @Column(nullable = false)
    private Long lastSequence;
}