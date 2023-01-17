package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record ClientReportDto(
        String id,
        LocalDateTime date,
        Double totalAmount,
        String transactionId
) {
}
