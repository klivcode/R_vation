package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.entity.BookingSequence;
import com.busreservationsystem.bus_reservation_system.repository.BookingSequenceRepo;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BookingReferenceGeneratorService {

    @Autowired
    private BookingSequenceRepo  bookingSequenceRepo;

    @Transactional
    public String generateReference() {

        LocalDate today = LocalDate.now();

        BookingSequence sequence =bookingSequenceRepo
                .findByDateForUpdate(today)
                .orElseGet(() -> {
                    BookingSequence newSeq = new BookingSequence();
                    newSeq.setBookingDate(today);
                    newSeq.setLastSequence(0L);
                    return newSeq;
                });

        Long nextSeq = sequence.getLastSequence() + 1;
        sequence.setLastSequence(nextSeq);

        bookingSequenceRepo.save(sequence);

        String datePart = today.format(DateTimeFormatter.BASIC_ISO_DATE);

        return String.format("BUS-%s-%04d", datePart, nextSeq);
    }
}

