package com.busreservationsystem.bus_reservation_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "tickets",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_ticket_number",
                        columnNames = {"ticket_number"}
                )
        }
)

@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number", nullable = false)
    private String ticketNumber;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
}