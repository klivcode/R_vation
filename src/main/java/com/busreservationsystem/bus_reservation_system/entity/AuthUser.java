package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import com.busreservationsystem.bus_reservation_system.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_username",
        columnNames = {"username"})
})
public class AuthUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role; //ADMIN, OPERATOR (COUNTER OPERATOR)

    private Boolean enabled; // instead of deleting a user row from users table, we can: enabled = false


}
