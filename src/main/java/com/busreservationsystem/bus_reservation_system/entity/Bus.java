package com.busreservationsystem.bus_reservation_system.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "buses",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_bus_number_company",
                        columnNames = {"bus_number", "company_id"}
                )
        }
)
@Data
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number", nullable = false)
    private String busNumber;

    private Integer totalSeats;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}