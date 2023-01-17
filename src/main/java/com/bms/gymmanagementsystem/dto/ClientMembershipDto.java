package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record ClientMembershipDto(
        String id,
        String status,
        LocalDateTime date
) {
}
