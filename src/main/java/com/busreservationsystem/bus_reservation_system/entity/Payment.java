package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import com.busreservationsystem.bus_reservation_system.enums.PaymentMethod;
import com.busreservationsystem.bus_reservation_system.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "payments", uniqueConstraints = {
        @UniqueConstraint(name = "uk_payment_reference",
        columnNames = "payment_reference"),

        @UniqueConstraint(name = "uk_boking_id",
        columnNames = {"booking_id"})
})
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_reference",nullable = false)
     private String paymentReference;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // CASH, CARD, ESEWA, KHALTI, BANK_TRANSFER

    @Enumerated(EnumType.STRING)
    private PaymentStatus  paymentStatus; // SUCCESS, REFUNDED, FAILURE

    private LocalDateTime paymentTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id")
    private Booking booking;


}
