package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record PaymentDto(
        String id,
        LocalDateTime date,
        Double amount,
        String clientId
) {
}
