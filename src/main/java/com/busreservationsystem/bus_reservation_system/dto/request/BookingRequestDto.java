package com.busreservationsystem.bus_reservation_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestDto {

    @NotBlank
    private String firstName;

    private String lastName;
    private Integer passengerCount;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private Long scheduleId;

    @NotEmpty
    private List<Long> seatIds;

    @NotNull
    @Positive
    private BigDecimal paidAmount;

}
