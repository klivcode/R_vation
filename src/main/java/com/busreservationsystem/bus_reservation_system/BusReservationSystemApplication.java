package com.busreservationsystem.bus_reservation_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class BusReservationSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(BusReservationSystemApplication.class, args);
    }

}
