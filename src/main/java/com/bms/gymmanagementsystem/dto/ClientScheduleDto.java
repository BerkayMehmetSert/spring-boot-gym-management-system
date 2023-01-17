package com.bms.gymmanagementsystem.dto;

import java.time.LocalDateTime;

public record ClientScheduleDto(
        String id,
        String session,
        String activity,
        LocalDateTime date,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String trainerId
) {
}
