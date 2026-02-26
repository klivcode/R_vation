package com.busreservationsystem.bus_reservation_system.entity;

import com.busreservationsystem.bus_reservation_system.common.BaseEntity;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import com.busreservationsystem.bus_reservation_system.enums.SeatStatus;
import com.busreservationsystem.bus_reservation_system.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_seat_schedule",
                        columnNames = {"seat_number", "schedule_id"}
                )
        }
)
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber; // A1,A2,B1,VIP1


    @Enumerated(EnumType.STRING)
    private SeatSide side; // A_SIDE or B_SIDE


    private Integer rowNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType; // NORMAL , VIP

//    @Enumerated(EnumType.STRING)
//    private SeatStatus seatStatus; // AVAILABLE, BOOKED


    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}