package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record ScheduleDto(
        String id,
        String session,
        String activity,
        LocalDateTime date,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String clientId,
        String trainerId
) {
}
