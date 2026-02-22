package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "companies",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_company_name",
                        columnNames = {"company_name"}
                )
        }
)
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

}