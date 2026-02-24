package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Bus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number", nullable = false)
    private String busNumber;

    private String busType; // AC " Non-AC ' Deluxe

    private Integer totalSeats;
    //Dynamic Layout support
    @Column(nullable = false)
    private String layoutPattern; // "2-2","2-1","3-2",.....

    private Boolean hasVipSeat;

    @Enumerated(EnumType.STRING)
    private SeatSide vipSeatSide;

    @Enumerated(EnumType.STRING)
    private SeatSide lastRowExtraSide; // A,B

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}