package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import com.busreservationsystem.bus_reservation_system.enums.BookingStatus;
import com.busreservationsystem.bus_reservation_system.enums.PaymentState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

// multiple seats booking and partial payment support too

@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_booking_reference",
                        columnNames = {"booking_reference"}
                ),
                @UniqueConstraint(
                        name = "uk_schedule_seat",
                        columnNames = {"schedule_id", "seat_number"}
                )
        }
)
@Data
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_reference", nullable = false)
    private String bookingReference;

    private LocalDateTime  bookingDateTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus; // CONFIRMED, CANCELLED

    private Double totalAmount;
    private Double paidAmount;
    private Double dueAmount;

    @Enumerated(EnumType.STRING)
    private PaymentState  paymentState;// FullY_PAID, PARTIALLY_PAID, UNPAID

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(
                    name = "booking_id"
            ),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private Set<Seat> seats;
}