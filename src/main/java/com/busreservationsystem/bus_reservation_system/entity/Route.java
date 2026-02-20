package com.busreservationsystem.bus_reservation_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "routes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_route_company",
                        columnNames = {"source", "destination", "company_id"}
                )
        }
)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}