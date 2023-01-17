package com.bms.gymmanagementsystem.dto;

import com.bms.gymmanagementsystem.model.Gender;

import java.util.Set;

public record TrainerDto(
        String id,
        String firstName,
        String lastName,
        Gender gender,
        Double salary,
        AddressDto address,
        Set<TrainerScheduleDto> schedules
) {
}
