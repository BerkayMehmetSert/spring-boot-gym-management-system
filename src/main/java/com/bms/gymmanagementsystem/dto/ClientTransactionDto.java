package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record ClientTransactionDto(
        String id,
        String name,
        Double amount,
        LocalDateTime date
) {
}
