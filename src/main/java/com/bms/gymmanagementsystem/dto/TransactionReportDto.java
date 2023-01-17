package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record TransactionReportDto(
        String id,
        LocalDateTime date,
        Double totalAmount,
        String clientId
) {
}
