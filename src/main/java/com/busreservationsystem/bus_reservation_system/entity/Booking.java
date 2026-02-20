package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.dto.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_booking_code",
                        columnNames = {"booking_code"}
                ),
                @UniqueConstraint(
                        name = "uk_schedule_seat",
                        columnNames = {"schedule_id", "seat_number"}
                )
        }
)
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_code", nullable = false)
    private String bookingCode;

    private String passengerName;
    private String phone;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}