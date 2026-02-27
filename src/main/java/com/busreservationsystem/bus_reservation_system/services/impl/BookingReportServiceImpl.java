package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.projection.SeatVerificationProjection;
import com.busreservationsystem.bus_reservation_system.repository.BookingRepo;
import com.busreservationsystem.bus_reservation_system.services.BookingReportService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingReportServiceImpl implements BookingReportService {
    @Autowired
    private BookingRepo bookingRepository;

    @Override
    public ByteArrayInputStream exportSeatVerificationExcel(Long scheduleId) throws IOException {

        List<SeatVerificationProjection> data =
                bookingRepository.findSeatVerificationData(scheduleId);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Seat Verification");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("✓");
        header.createCell(1).setCellValue("Seat");
        header.createCell(2).setCellValue("Customer Name");
        header.createCell(3).setCellValue("Paid");
        header.createCell(4).setCellValue("Due");

        int rowIdx = 1;

        for (SeatVerificationProjection seat : data) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue("");
            row.createCell(1).setCellValue(seat.getSeatNumber());
            row.createCell(2).setCellValue(seat.getCustomerName());
            row.createCell(3).setCellValue(seat.getPaidAmount().doubleValue());
            row.createCell(4).setCellValue(seat.getDueAmount().doubleValue());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
