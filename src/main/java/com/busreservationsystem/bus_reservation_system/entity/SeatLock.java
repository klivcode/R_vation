package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.entity.Schedule;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "seat_locks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_schedule_locked_seat",
                        columnNames = {"schedule_id", "seat_number"}
                )
        }
)
@Data
public class SeatLock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    private LocalDateTime lockedAt;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}