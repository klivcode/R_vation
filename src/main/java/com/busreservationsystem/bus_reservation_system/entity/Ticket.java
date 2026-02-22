package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number", nullable = false)
    private String ticketNumber;

    private LocalDateTime issueDate;
    private String qrCodeUrl;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
}