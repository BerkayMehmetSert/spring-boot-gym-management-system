package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record MembershipDto(
        String id,
        String status,
        LocalDateTime date,
        String clientId
) {
}
