package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record ClientPaymentDto(
        String id,
        LocalDateTime date,
        Double amount
) {
}
