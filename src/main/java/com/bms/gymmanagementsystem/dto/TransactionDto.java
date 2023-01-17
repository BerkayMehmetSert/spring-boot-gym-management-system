package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record TransactionDto(
        String id,
        String name,
        Double amount,
        LocalDateTime date,
        String clientId,
        Set<TransactionReportDto> reports
) {
}
