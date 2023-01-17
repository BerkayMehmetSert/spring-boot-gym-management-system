package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record ReportDto(
        String id,
        LocalDateTime date,
        Double totalAmount,
        String clientId,
        String transactionId
) {
}
