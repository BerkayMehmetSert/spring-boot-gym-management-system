package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record TrainerScheduleDto(
        String id,
        String session,
        String activity,
        LocalDateTime date,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String clientId
) {
}
