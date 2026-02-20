package com.busreservationsystem.bus_reservation_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_bus_seat_number",
                        columnNames = {"bus_id", "seat_number"}
                )
        }
)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String side; // A_SIDE or B_SIDE

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
}