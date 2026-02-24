package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import com.busreservationsystem.bus_reservation_system.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "customers",
uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_customer_phoneNumber",
                columnNames = {"phone_number"}
        )
})
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String firstName;
    private String lastName;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
