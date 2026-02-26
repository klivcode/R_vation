package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.Bus;
import com.busreservationsystem.bus_reservation_system.entity.BusSeat;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.enums.SeatSide;
import com.busreservationsystem.bus_reservation_system.enums.SeatType;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.BusRepo;
import com.busreservationsystem.bus_reservation_system.repository.BusSeatRepo;
import com.busreservationsystem.bus_reservation_system.repository.CompanyRepo;
import com.busreservationsystem.bus_reservation_system.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepo  busRepo;
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private BusSeatRepo busSeatRepo;


    @Override
    public BusResponseDto registerBus(BusRequestDto busRequestDto) {

        Company company = companyRepo.findById(busRequestDto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        Bus bus = new Bus();
        bus.setBusNumber(busRequestDto.getBusNumber());
        bus.setBusType(busRequestDto.getBusType());
        bus.setHasVipSeat(busRequestDto.getHasVipSeat());
        bus.setVipSeatSide(busRequestDto.getVipSide());
        bus.setTotalSeats(busRequestDto.getTotalSeats());
        bus.setLayoutPattern(busRequestDto.getLayoutPattern());
        bus.setLastRowExtraSide(busRequestDto.getLastRowExtraSide());
        bus.setCompany(company);

        Bus savedBus = busRepo.save(bus);

        // generate the template seats
        generateBusSeats(savedBus);



        return new BusResponseDto(
                savedBus.getId(),
                savedBus.getCreatedAt(),
                savedBus.getUpdatedAt(),
                savedBus.getBusNumber(),
                savedBus.getTotalSeats(),
                savedBus.getBusType(),
                savedBus.getLayoutPattern(),
                savedBus.getHasVipSeat(),
                savedBus.getVipSeatSide(),
                savedBus.getLastRowExtraSide(),
                savedBus.getCompany().getId(),
                savedBus.getCompany().getCompanyName()
        );
    }

    @Override
    public Page<BusResponseDto> getAllBus(Pageable pageable) {
        Page <Bus> Bus  = busRepo.findAll(pageable);
        return Bus
                .map(bus->new BusResponseDto(
                        bus.getId(),
                        bus.getCreatedAt(),
                        bus.getUpdatedAt(),
                        bus.getBusNumber(),
                        bus.getTotalSeats(),
                        bus.getBusType(),
                        bus.getLayoutPattern(),
                        bus.getHasVipSeat(),
                        bus.getLastRowExtraSide(),
                        bus.getVipSeatSide(),
                        bus.getCompany().getId(),
                        bus.getCompany().getCompanyName()

                ));
    }

    @Override
    public BusResponseDto getBusById(long id) {
        Bus bus= busRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return new BusResponseDto(
                bus.getId(),
                bus.getCreatedAt(),
                bus.getUpdatedAt(),
                bus.getBusNumber(),
                bus.getTotalSeats(),
                bus.getBusType(),
                bus.getLayoutPattern(),
                bus.getHasVipSeat(),
                bus.getLastRowExtraSide(),
                bus.getVipSeatSide(),
                bus.getCompany().getId(),
                bus.getCompany().getCompanyName()

        );
    }

    @Override
    public BusResponseDto updateBusById(BusRequestDto busRequestDto, long id) {
        Bus bus = busRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Company not found"));
        bus.setBusNumber(busRequestDto.getBusNumber());
        bus.setBusType(busRequestDto.getBusType());
        bus.setHasVipSeat(busRequestDto.getHasVipSeat());
        bus.setVipSeatSide(busRequestDto.getVipSide());
        bus.setTotalSeats(busRequestDto.getTotalSeats());
        bus.setLayoutPattern(busRequestDto.getLayoutPattern());
        bus.setLastRowExtraSide(busRequestDto.getLastRowExtraSide());
        bus.setCompany(bus.getCompany());
        Bus savedBus = busRepo.save(bus);
        return new BusResponseDto(
                savedBus.getId(),
                savedBus.getCreatedAt(),
                savedBus.getUpdatedAt(),
                savedBus.getBusNumber(),
                savedBus.getTotalSeats(),
                savedBus.getBusType(),
                savedBus.getLayoutPattern(),
                savedBus.getHasVipSeat(),
                savedBus.getVipSeatSide(),
                savedBus.getLastRowExtraSide(),
                savedBus.getCompany().getId(),
                savedBus.getCompany().getCompanyName()
        );


    }

    @Override
    public void deleteBusById(Long id) {
        Bus bus = busRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        busRepo.delete(bus);
    }

    @Override
    public Page<BusResponseDto> searchBus(String busNumber, Boolean hasVipSeat, String busType, Long companyId, Pageable pageable) {
        Specification<Bus> spec = (root, query, cb) -> cb.conjunction();

        if (busNumber != null && !busNumber.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(
                            cb.lower(root.get("busNumber")),
                            busNumber.toLowerCase() + "%"
                    )
            );
        }

        if (hasVipSeat != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("hasVipSeat"), hasVipSeat)
            );
        }

        if (busType != null && !busType.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(
                            cb.lower(root.get("busType")),
                            busType.toLowerCase()
                    )
            );
        }

        if (companyId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("company").get("id"), companyId)
            );
        }

        Page<Bus> buses = busRepo.findAll(spec, pageable);

        return buses
                .map(bus->new BusResponseDto(
                        bus.getId(),
                        bus.getCreatedAt(),
                        bus.getUpdatedAt(),
                        bus.getBusNumber(),
                        bus.getTotalSeats(),
                        bus.getBusType(),
                        bus.getLayoutPattern(),
                        bus.getHasVipSeat(),
                        bus.getLastRowExtraSide(),
                        bus.getVipSeatSide(),
                        bus.getCompany().getId(),
                        bus.getCompany().getCompanyName()
                ));
    }






    // Parse the layout
    private int[] parseLayout(String layoutPattern) {

        String[] parts = layoutPattern.split("-");

        return new int[]{
                Integer.parseInt(parts[0]), // A side
                Integer.parseInt(parts[1])  // B side
        };
    }

    // vip naming Logic
    private String buildSeatNumber(Bus bus,
                                   SeatSide side,
                                   int counter,
                                   int row) {

        boolean isVipRow = bus.getHasVipSeat()
                && row == 1;

        boolean isVipSide = side == bus.getVipSeatSide();

        if (isVipRow && isVipSide) {
            return "VIP" + side.name();
        }

        return side.name() + counter;
    }

    // vip seat type logic
    private SeatType resolveSeatType(Bus bus,
                                     int row,
                                     SeatSide side) {

        if (bus.getHasVipSeat()
                && row == 1
                && side == bus.getVipSeatSide()) {

            return SeatType.VIP;
        }

        return SeatType.NORMAL;
    }

    private void generateBusSeats(Bus bus) {

        // 🔹 Parse Layout (example: "2-2")
        int[] layout = parseLayout(bus.getLayoutPattern());

        int leftSeatsPerRow = layout[0];   // A side
        int rightSeatsPerRow = layout[1];  // B side
        int seatsPerRow = leftSeatsPerRow + rightSeatsPerRow;

        int totalSeats = bus.getTotalSeats();

        int fullRows = totalSeats / seatsPerRow;
        int remainder = totalSeats % seatsPerRow;

        List<BusSeat> seatList = new ArrayList<>();

        int aCounter = 1;
        int bCounter = 1;

        // 🔹 GENERATE FULL ROWS
        for (int row = 1; row <= fullRows; row++) {

            // A SIDE
            for (int i = 0; i < leftSeatsPerRow; i++) {

                BusSeat seat = new BusSeat();
                seat.setBus(bus);
                seat.setRowNumber(row);
                seat.setSide(SeatSide.A);
                seat.setSeatNumber("A" + aCounter++);
                seat.setSeatType(SeatType.NORMAL);

                seatList.add(seat);
            }

            // B SIDE
            for (int i = 0; i < rightSeatsPerRow; i++) {

                BusSeat seat = new BusSeat();
                seat.setBus(bus);
                seat.setRowNumber(row);
                seat.setSide(SeatSide.B);
                seat.setSeatNumber("B" + bCounter++);
                seat.setSeatType(SeatType.NORMAL);

                seatList.add(seat);
            }
        }

        //  ATTACH EXTRA SEATS TO LAST FULL ROW
        if (remainder > 0) {

            SeatSide extraSide = bus.getLastRowExtraSide();

            for (int i = 0; i < remainder; i++) {

                BusSeat seat = new BusSeat();
                seat.setBus(bus);

                // attach to last row
                seat.setRowNumber(fullRows);

                seat.setSide(extraSide);

                if (extraSide == SeatSide.A) {
                    seat.setSeatNumber("A" + aCounter++);
                } else {
                    seat.setSeatNumber("B" + bCounter++);
                }

                seat.setSeatType(SeatType.NORMAL);

                seatList.add(seat);
            }
        }

        // APPLY VIP (FIRST ROW ONLY)
        if (bus.getHasVipSeat()) {

            SeatSide vipSide = bus.getVipSeatSide();

            int vipCounter = 1;

            for (BusSeat seat : seatList) {

                if (seat.getRowNumber() == 1 &&
                        seat.getSide() == vipSide) {

                    seat.setSeatNumber(
                            vipSide.name() + "-VIP" + vipCounter++
                    );

                    seat.setSeatType(SeatType.VIP);
                }
            }
        }

        busSeatRepo.saveAll(seatList);
    }

}
